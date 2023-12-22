## Name : Ian Hurley

## Student Number: 20099695

# TECHNICAL REPORT/ README.MD

### Brief description of Android App:

'Wild Swimming' is a placemark application designed to manage and organise a users Swimspots.

V2.0.0 adopts the MVVM Architectural Design Pattern utilising UI Fragments and View Model design. The main navigation is through Androids Navigation drawer, consisting of a login id header and list of active routes.

Login support is provided through Firebase Authentication, with sign-in methods provided through regular email/password and Google Sign-In, with multi-user support.

Data persistence is supported through Firebase Realtime Database through data synchronization.


## How to use/ what to expect:

Users can log Swimspots Name, County and body of water Categorey. Upload photo and map location (not fully re-implemented in V2)
Swimspots can be edited/updated or deleted as necessary from the database.

## Known bugs/problems:

Image selecting for Swimspot not yet persisted with database, and not visible within card detail.

## UML Class Diagrams:

![uml.png](app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fuml.png)
![uml2.png](app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fuml2.png)

## UX/DX Approach Adopted:

The UX/DX approach expands on the earlier V1.0.0, maintaining a similar colour palette, custom button designs and aligning the approach with the introduction of navigating and authentication features.

## Personal Statement:

I found Assignment 2 quite challenging in transitioning from the earlier module topics 1-6, though the second half of the module topics 7-12.

I began the assessment with the clear intention of forking my tagged release v1.0.0 and continuing to refactor my assignment into a progressed v2.0.0 release. This proved very time consuming and eventually led me to reset and rebuild for Assignment 2 in line with the latter module topics.

While I understand coursework can only cover so much, there are areas between topic jumps that were not fully understood to me. Understanding these transitions in a way maybe limits experimental work for those unfamiliar with Android/Kotlin programming.

In summary, I feel V2.0.0, while achieving some of the key elements, has not progressed beyond the initial V1.0.0 release to a point that I feel satisfied with. And that may be purely down to time management with lectures, labs, assignment and study work during the second half of this module.


## Sources/ References:

Mobile Application Development (HDip) Lecture Series

Inspired by the book 'The Art of Wild Swimming - Ireland'
https://developer.android.com/develop
Custom button designs https://www.youtube.com/watch?v=nlPtfncjOWA
Splash Screen API Method https://www.youtube.com/watch?v=Eu7lkrDjBq8
Spinner Tutorial https://www.youtube.com/watch?v=PjW-XiQ6usI