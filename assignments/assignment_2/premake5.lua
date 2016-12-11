-- premake5.lua
workspace "Assignment 2"
   configurations { "Debug", "Release" }

project "Problem2_1"
   kind "ConsoleApp"
   language "C"
   targetdir "bin/%{cfg.buildcfg}"

   files { "problem2_1.c" }

   filter "configurations:Debug"
      defines { "DEBUG" }
      flags { "Symbols" }

   filter "configurations:Release"
      defines { "NDEBUG" }

   configuration { "linux", "gmake" }
       linkoptions { "-lm" }

project "Problem2_2"
   kind "ConsoleApp"
   language "C"
   targetdir "bin/%{cfg.buildcfg}"

   files { "problem2_2.c" }

   filter "configurations:Debug"
      defines { "DEBUG" }
      flags { "Symbols" }

   filter "configurations:Release"
      defines { "NDEBUG" }
