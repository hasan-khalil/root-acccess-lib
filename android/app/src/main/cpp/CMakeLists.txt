#CMakeLists.txt
cmake_minimum_required(VERSION 3.22.1)
project(ExploitApp)

# معايير C
set(CMAKE_C_STANDARD 17)
set(CMAKE_C_STANDARD_REQUIRED ON)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O2 -Wall -Wextra -fPIC")
set(CMAKE_BUILD_TYPE Debug)
add_compile_options(-g -O0)


# إضافة headers
include_directories(
        ${CMAKE_CURRENT_SOURCE_DIR}/exploit
        ${CMAKE_CURRENT_SOURCE_DIR}/exploit/src
        ${CMAKE_CURRENT_SOURCE_DIR}/exploit/src/kernelsnitch
)

# ملفات الـ C الأساسية
set(EXPLOIT_SOURCES
        exploit/src/main.c
        exploit/src/util.c
        exploit/src/fops.c
        exploit/src/slide.c
        exploit/src/pipe.c
        exploit/src/root.c
)

# مكتبة JNI Bridge
add_library(exploit-lib SHARED
        native-lib.cpp
        ${EXPLOIT_SOURCES}
)

# ربط المكتبات
target_link_libraries(exploit-lib
        android
        log
)

# تعريفات المترجم
target_compile_definitions(exploit-lib PRIVATE
        TARGET_CONFIG_H="targets/blazer-CP2A.260605.012/target.h"
        __ARM=1
        _GNU_SOURCE
)

# تحسينات الأمان
target_compile_options(exploit-lib PRIVATE
        -fstack-protector-strong
        -fPIE
        -fPIC
        -pthread
)

target_link_options(exploit-lib PRIVATE -pthread)