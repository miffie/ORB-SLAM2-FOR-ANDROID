

cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(libs_DIR ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI})


include_directories( ${CMAKE_SOURCE_DIR}/libs/include )

add_library(OpenCV_java3 SHARED IMPORTED )
set_target_properties(  OpenCV_java3
                        PROPERTIES IMPORTED_LOCATION
                        ${libs_DIR}/libopencv_java3.so
                        )

add_library(g2o SHARED IMPORTED )
set_target_properties(  g2o
                        PROPERTIES IMPORTED_LOCATION
                        ${libs_DIR}/libg2o.so
                        )
add_library(DBoW2 SHARED IMPORTED )
set_target_properties(  DBoW2
                        PROPERTIES IMPORTED_LOCATION
                        ${libs_DIR}/libDBoW2.so
                        )

add_library(ORB_SLAM2 SHARED IMPORTED )
set_target_properties(  ORB_SLAM2
                        PROPERTIES IMPORTED_LOCATION
                        ${libs_DIR}/libORB_SLAM2.so
                        )


add_library(
             native-lib

             SHARED

             src/main/cpp/native-lib.cpp )



find_library(
              log-lib

              log )


target_link_libraries(
                       native-lib
                       OpenCV_java3

                       g2o
                       DBoW2
                       ORB_SLAM2

                       GLESv1_CM
                       EGL
                       android
                       ${log-lib} )