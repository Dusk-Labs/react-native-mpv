project(player)

cmake_minimum_required(VERSION 3.4.1)

option(MPV_LIBRARY_PREFIX "MPV_LIBRARY_PREFIX" "libs/")

set(ABI_PREFIX "${MPV_LIBRARY_PREFIX}/${ANDROID_ABI}")

add_library(player SHARED
            main.cpp
            render.cpp
            log.cpp
            jni_utils.cpp
            property.cpp
            event.cpp
            thumbnail.cpp)

# Link libmpv.so and include its headers
add_library(libmpv SHARED IMPORTED)
set_target_properties(libmpv PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libmpv.so)
include_directories(${ABI_PREFIX}/include)

# Link libswresample and include its headers
add_library(libswresample SHARED IMPORTED)
set_target_properties(libswresample PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libswresample.so)

# Link libpostproc
add_library(libpostproc SHARED IMPORTED)
set_target_properties(libpostproc PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libpostproc.so)

# Link libavutil
add_library(libavutil SHARED IMPORTED)
set_target_properties(libavutil PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libavutil.so)

# Link libavformat
add_library(libavformat SHARED IMPORTED)
set_target_properties(libavformat PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libavformat.so)

# Link libswscale
add_library(libswscale SHARED IMPORTED)
set_target_properties(libswscale PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libswscale.so)

# Link libavfilter
add_library(libavfilter SHARED IMPORTED)
set_target_properties(libavfilter PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libavfilter.so)

# Link libavdevice
add_library(libavdevice SHARED IMPORTED)
set_target_properties(libavdevice PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libavdevice.so)

# Link libavcodec
add_library(libavcodec SHARED IMPORTED)
set_target_properties(libavcodec PROPERTIES IMPORTED_LOCATION ${ABI_PREFIX}/lib/libavcodec.so)

find_library(log-lib log)

target_link_libraries(player libmpv libswresample libpostproc libavutil libavformat libswscale libavfilter libavdevice libavcodec ${log-lib})
