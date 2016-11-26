-- premake5.lua
workspace "Assignment 3"
   configurations { "Debug", "Release" }

project "CellPrices"
   kind "ConsoleApp"
   language "C++"
   targetdir "bin/%{cfg.buildcfg}"

   files { "cell_prices.cpp" }

   filter "configurations:Debug"
      defines { "DEBUG" }
      flags { "Symbols" }

   filter "configurations:Release"
      defines { "NDEBUG" }

   configuration { "linux", "gmake" }
       linkoptions { "-lm" }
