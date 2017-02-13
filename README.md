# utabstraction
Unit Test Abstraction codebases

This Github repo will contain source bases for the 2017 Agile Technical Conference to be held April 19-21 in Boston, MA, US.

## The Talk

Here's a link to a page summarizing my talk, which will be Thursday afternoon (April 20) at 3:20pm:

https://aatc2017.sched.com/event/9PAe/unit-test-abstraction-crafting-sustainable-tests-jeff-langr

I see that Uncle Bob is presenting at the same time. Bob is highly entertaining, and SOLID is good stuff, but if you've seen either, come to this talk and have fun whittling some nasty tests into something you'll want to maintain!

## The Codebase(s)

This is the start of an app I'm building for the Raspberry Pi. It manages a home pantry. At some point it will allow for scanning of barcodes; right now it looks up UPC codes using an open API, and returns basic product details. The primary goal will be to track when items are near expiration.

See how long it takes you to spot the nasty test class that we'll be shaping up.

The codebases are ready to go! Feel free to clone, but realize that you might need to pull prior to my talk.

## Java

The Java codebase uses Java 8 and Gradle to pull dependencies (which include JUnit, Mockito, Hamcrest, and a few odds & ends).

## C# #

The C# codebase uses .NET 4.0 / C# 4.0. It has dependences on NUnit, Moq, and Newtonsoft.Json. NuGet should load dependencies when you open the solution in VS.

## More on Me Etc.

About: http://langrsoft.com/about/
