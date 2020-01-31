# Basic TD Directory

A very simple Java/servlet application that performs the usual directory operations over Web of Things (WoT) Thing Descriptions (TD) as specified in the [W3C WoT TD Proposed Recommendation](https://www.w3.org/TR/2020/PR-wot-thing-description-20200130/) of 2020/01/30.

As it performs validation against an internal copy of the JSON schema from the TD spec, this webapp is standalone and suitable for environments without Internet access (intranet, local VM...).

## API

The API is much more restricted than that of the reference [ThingWeb Directory](https://github.com/thingweb/thingweb-directory) and only works over HTTP.
It allows the following operations:

- `/bind`: POST an application/x-www-form-urlencoded form with 2 parameters (`tdName` and `tdContent`) to (re)bind a TD; fails with error 412 in case of validation error.
- `/unbind`: POST an application/x-www-form-urlencoded form with 1 parameter (`tdName`) to remove a TD.
- `/lookup`: GET the TD bound with the name in the `tdName` parameter.
- `/list`: GET the list of all bound TD names

## Installation

Clone the repository, build the app using `mvn package`, and deploy the `target/TD.war` file on your favorite servlet container.

Tested with OpenJDK 11 and Tomcat 9.

## Usage

At context root is a Single-Page Application client allowing you to test the server-side directory.

Documentation may come one day. Meanwhile, use your browser's Developer Tools to visualize the (very simple) server API.

## Evolution

To upgrade TD validation against a newer schema version, just replace the `src/main/resources/td-schema.json` file.

## Disclaimer

This software is provided as-is, and is not intended to be supported in the future. It has been developed for testing and teaching purposes. In particular, no persistence layer has been implemented. Feel free to replace the in-memory Java Map by any DB of your choice.

## License

The project itself is licensed under the
[Creative Commons Zero v1.0 Universal](./LICENSE).
It may be subject to more restrictive licenses due to the use of libraries among which: [Jackson](https://github.com/FasterXML/jackson) and [JSON Schema Validator](https://github.com/everit-org/json-schema).