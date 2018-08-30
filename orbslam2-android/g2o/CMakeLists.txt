cmake_minimum_required(VERSION 3.4.1)

include_directories(${CMAKE_SOURCE_DIR}/libs/include)

add_library(
             g2o

             SHARED

             src/main/cpp/native-lib.cpp

             src/main/cpp/Thirdparty/g2o/g2o/types/types_sba.cpp
             src/main/cpp/Thirdparty/g2o/g2o/types/types_six_dof_expmap.cpp
             src/main/cpp/Thirdparty/g2o/g2o/types/types_seven_dof_expmap.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/hyper_graph_action.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/hyper_graph.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/marginal_covariance_cholesky.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/matrix_structure.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/batch_stats.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/parameter.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/cache.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/optimizable_graph.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/solver.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/optimization_algorithm_factory.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/estimate_propagator.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/factory.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/sparse_optimizer.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/hyper_dijkstra.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/parameter_container.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/optimization_algorithm.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/optimization_algorithm_with_hessian.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/optimization_algorithm_levenberg.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/jacobian_workspace.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/robust_kernel.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/robust_kernel_factory.cpp
             src/main/cpp/Thirdparty/g2o/g2o/core/robust_kernel_impl.cpp
             src/main/cpp/Thirdparty/g2o/g2o/stuff/timeutil.cpp
             src/main/cpp/Thirdparty/g2o/g2o/stuff/os_specific.c
             src/main/cpp/Thirdparty/g2o/g2o/stuff/string_tools.cpp
             src/main/cpp/Thirdparty/g2o/g2o/stuff/property.cpp
              )


find_library(
              log-lib

              log )
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
set(CMAKE_VERBOSE_MAKEFILE on)
set(distribution_DIR ${CMAKE_SOURCE_DIR}/../app)
set_target_properties(g2o PROPERTIES
                      LIBRARY_OUTPUT_DIRECTORY
                      ${distribution_DIR}/libs/${ANDROID_ABI})


target_link_libraries(
                       g2o

                       ${log-lib} )