[![Build Status](https://travis-ci.org/Igor-ua/name-filter.svg?branch=master)](https://travis-ci.org/Igor-ua/name-filter)

**Name Filter**

**API:**</br>
`"/"`</br>
`"/hello/contacts?nameFilter=regExp"`</br>
`"/hello/contacts?nameFilter=regExp?page=value"`</br>

**Pagination:**</br>
Get request `"/hello/contacts?nameFilter=regExp"` redirects to another URL with `page` value.<br>
Response obtains values:<br>
`"X-Pagination-Page"`</br>
`"X-Pagination-PageSize"`</br>
`"X-Pagination-HasMore"`</br>

**How-to:**</br>
Requires java 8<br>
`"mvn package"` builds "uber-jar" with an embedded (h2) database inside (does not need `vagrant` or anything else).<br>
Runs from the `/target dir`: `java -jar name-filter-0.0.1-SNAPSHOT.jar` (by default: `http://localhost:8586/`)