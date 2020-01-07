cmake_minimum_required(VERSION 3.15)
project(des_fin)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -fopenmp")
add_executable(des_fin main.cpp text_converter.h text_converter.cpp des.h key_trasformer.cpp key_trasformer.h des.cpp)