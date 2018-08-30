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


add_library(
             ORB_SLAM2

             SHARED

             src/main/cpp/native-lib.cpp
             src/main/cpp/ORB_SLAM2/System.cc
             src/main/cpp/ORB_SLAM2/Tracking.cc
             src/main/cpp/ORB_SLAM2/LocalMapping.cc
             src/main/cpp/ORB_SLAM2/LoopClosing.cc
             src/main/cpp/ORB_SLAM2/ORBextractor.cc
             src/main/cpp/ORB_SLAM2/ORBmatcher.cc
             src/main/cpp/ORB_SLAM2/FrameDrawer.cc
             src/main/cpp/ORB_SLAM2/Converter.cc
             src/main/cpp/ORB_SLAM2/MapPoint.cc
             src/main/cpp/ORB_SLAM2/KeyFrame.cc
             src/main/cpp/ORB_SLAM2/Map.cc
             src/main/cpp/ORB_SLAM2/Optimizer.cc
             src/main/cpp/ORB_SLAM2/PnPsolver.cc
             src/main/cpp/ORB_SLAM2/Frame.cc
             src/main/cpp/ORB_SLAM2/KeyFrameDatabase.cc
             src/main/cpp/ORB_SLAM2/Sim3Solver.cc
             src/main/cpp/ORB_SLAM2/Initializer.cc
             src/main/cpp/ORB_SLAM2/MapDrawer.cc
             src/main/cpp/ORB_SLAM2/Viewer.cc
              )


find_library(
              log-lib

              log )

set(distribution_DIR ${CMAKE_SOURCE_DIR}/../app)
set_target_properties(ORB_SLAM2 PROPERTIES
                      LIBRARY_OUTPUT_DIRECTORY
                      ${distribution_DIR}/libs/${ANDROID_ABI})
target_link_libraries(
                       ORB_SLAM2
                       OpenCV_java3
                       g2o
                       DBoW2

                       GLESv1_CM
                       EGL
                       android
                       ${log-lib} )