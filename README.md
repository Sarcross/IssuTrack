# IssuTrack
A Java based application to quickly create and track issues. This program uses JavaFX and requires Java 8 to function correctly.


##Overview
IssuTrack was created out of the need to document and track issues for projects that could not be hosted by an online repository. With IssuTrack you are able to create, assign, edit, and complete tasks and issues central to your project.

##How to Use
IssuTrack is incredibly simple to use. You can either download and build the project yourself or run the *IssuTrack.jar* file.

Click "New..." to create a new issue. From there enter the:
* Issue name
* Issue creator
* Issue assignee
* Issue description

Assigning an issue to a person is not required. After an issue is created it is added to the list where it can be edited or deleted.

Issue sets are saved in .XML format with an element heirarchy as follows:
```xml
<issues>
    <issue>
      <assignedTo>Assignee Name</assignedTo>
      <created>2015-08-09</created>
      <creator>Creator Name</creator>
      <description>Description of the issue</description>
      <finished>-999999999-01-01</finished>
      <name>Issue Name</name>
  </issue>
</issues>
```

This allows the possibility of easily creating an issue set without the need of IssuTrack. Issues with a finished value of -999999999-01-01, `LocalDate.MIN`, have not been finished.
