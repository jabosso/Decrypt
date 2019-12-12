cmake_minimum_required(VERSION 3.15)
project(PC1)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -fopenmp")

set(SOURCE_FILES main.cpp)

add_executable(PC1 main.cpp keyGen.h)