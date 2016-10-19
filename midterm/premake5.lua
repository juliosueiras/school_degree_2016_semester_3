-- premake5.lua
workspace "Midterm"
   configurations { "Debug", "Release" }

project "Midterm"
   kind "ConsoleApp"
   language "C"
   targetdir "bin/%{cfg.buildcfg}"

   files { "midterm.c" }

   filter "configurations:Debug"
      defines { "DEBUG" }
      flags { "Symbols" }

   filter "configurations:Release"
      defines { "NDEBUG" }

   configuration { "linux", "gmake" }
       linkoptions { "-lm" }
