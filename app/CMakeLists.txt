cmake_minimum_required(VERSION 3.4.1)

add_library(Hello
            SHARED
            src/main/cpp/Hello.c
            src/main/cpp/Test.c
            src/main/cpp/Log.c)

find_library(log-lib log)

target_link_libraries(Hello ${log-lib})