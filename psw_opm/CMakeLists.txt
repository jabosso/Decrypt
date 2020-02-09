cmake_minimum_required(VERSION 3.15)
project(psw_opm)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -fopenmp")

set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -O0")
add_executable(psw_opm main.cpp text_converter.h text_converter.cpp des.h key_trasformer.cpp key_trasformer.h des.cpp database.cpp database.h)