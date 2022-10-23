#!/bin/bash -ex

cd "$( dirname "${BASH_SOURCE[0]}" )"
. ./include/depinfo.sh

cleanbuild=0
nodeps=0
clang=1
target=mpv
arch=armv7l

getdeps () {
	varname="dep_${1//-/_}[*]"
	echo ${!varname}
}

loadarch () {
	unset CC CXX CPATH LIBRARY_PATH C_INCLUDE_PATH CPLUS_INCLUDE_PATH

	local apilvl=21
	# ndk_triple: what the toolchain actually is
	# cc_triple: what Google pretends the toolchain is
	if [ "$1" == "armv7l" ]; then
		export ndk_suffix=
		export ndk_triple=arm-linux-androideabi
		cc_triple=armv7a-linux-androideabi$apilvl
		prefix_name=armv7l
	elif [ "$1" == "arm64" ]; then
		export ndk_suffix=-arm64
		export ndk_triple=aarch64-linux-android
		cc_triple=$ndk_triple$apilvl
		prefix_name=arm64
	elif [ "$1" == "x86" ]; then
		export ndk_suffix=-x86
		export ndk_triple=i686-linux-android
		cc_triple=$ndk_triple$apilvl
		prefix_name=x86
	elif [ "$1" == "x86_64" ]; then
		export ndk_suffix=-x64
		export ndk_triple=x86_64-linux-android
		cc_triple=$ndk_triple$apilvl
		prefix_name=x86_64
	else
		echo "Invalid architecture"
		exit 1
	fi

	export prefix_dir="$PWD/prefix/$prefix_name"
        export CC=$cc_triple-clang
        export CXX=$cc_triple-clang++
	export AR=llvm-ar
	export RANLIB=llvm-ranlib
}

setup_prefix () {
	if [ ! -d "$prefix_dir" ]; then
		mkdir -p "$prefix_dir"
		# enforce flat structure (/usr/local -> /)
		ln -s . "$prefix_dir/usr"
		ln -s . "$prefix_dir/local"
	fi

	local cpu_family=${ndk_triple%%-*}
	[ "$cpu_family" == "i686" ] && cpu_family=x86

	# meson wants to be spoonfed this file, so create it ahead of time
	# also define: release build, static libs and no source downloads at runtime(!!!)
	cat >"$prefix_dir/crossfile.txt" <<CROSSFILE
[built-in options]
buildtype = 'release'
default_library = 'static'
wrap_mode = 'nodownload'
[binaries]
c = '$CC'
cpp = '$CXX'
ar = 'llvm-ar'
strip = '$ndk_triple-strip'
pkgconfig = 'pkg-config'
[host_machine]
system = 'android'
cpu_family = '$cpu_family'
cpu = '${CC%%-*}'
endian = 'little'
CROSSFILE
}

build () {
	if [ ! -d deps/$1 ]; then
		printf >&2 '\e[1;31m%s\e[m\n' "Target $1 not found"
		return 1
	fi

	if [ $nodeps -eq 0 ]; then
		printf >&2 '\e[1;34m%s\e[m\n' "Preparing $1..."
		local deps=$(getdeps $1)
		echo >&2 "Dependencies: $deps"
		for dep in $deps; do
			build $dep
		done
	fi

	printf >&2 '\e[1;34m%s\e[m\n' "Building $1..."
        pushd deps/$1
        BUILDSCRIPT=../../scripts/$1.sh

	[ $cleanbuild -eq 1 ] && $BUILDSCRIPT clean
	$BUILDSCRIPT build
	popd
}

usage () {
	printf '%s\n' \
		"Usage: buildall.sh [options] [target]" \
		"Builds the specified target (default: $target)" \
		"-n             Do not build dependencies" \
		"--clean        Clean build dirs before compiling" \
		"--gcc          Use gcc compiler (unsupported!)" \
		"--arch <arch>  Build for specified architecture (default: $arch; supported: armv7l, arm64, x86, x86_64)"
	exit 0
}

while [ $# -gt 0 ]; do
	case "$1" in
		--clean)
		cleanbuild=1
		;;
		-n|--no-deps)
		nodeps=1
		;;
		--gcc)
		clang=0
		;;
		--arch)
		shift
		arch=$1
		;;
		-h|--help)
		usage
		;;
		*)
		target=$1
		;;
	esac
	shift
done

loadarch $arch
setup_prefix
build $target

exit 0
