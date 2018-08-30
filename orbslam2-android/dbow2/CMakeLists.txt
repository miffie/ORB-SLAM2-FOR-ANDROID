
cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
set(libs_DIR ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI})


include_directories(${CMAKE_SOURCE_DIR}/libs/include)

add_library(OpenCV_java3 SHARED IMPORTED )
set_target_properties(  OpenCV_java3
                        PROPERTIES IMPORTED_LOCATION
                        ${libs_DIR}/libopencv_java3.so
                        )

#SET_TARGET_PROPERTIES(DBoW2 PROPERTIES LINKER_LANGUAGE C++)
add_library(
             DBoW2

             SHARED
             src/main/cpp/native-lib.cpp
             src/main/cpp/Thirdparty/DBoW2/DBoW2/BowVector.cpp
             src/main/cpp/Thirdparty/DBoW2/DBoW2/FORB.cpp
             src/main/cpp/Thirdparty/DBoW2/DBoW2/FeatureVector.cpp
             src/main/cpp/Thirdparty/DBoW2/DBoW2/ScoringObject.cpp
             src/main/cpp/Thirdparty/DBoW2/DUtils/Random.cpp
             src/main/cpp/Thirdparty/DBoW2/DUtils/Timestamp.cpp
              )



find_library(
              log-lib

              log )
set(distribution_DIR ${CMAKE_SOURCE_DIR}/../app)
set_target_properties(DBoW2 PROPERTIES
                      LIBRARY_OUTPUT_DIRECTORY
                      ${distribution_DIR}/libs/${ANDROID_ABI})

target_link_libraries(
                       DBoW2
                       OpenCV_java3

                       ${log-lib} )