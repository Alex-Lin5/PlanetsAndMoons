1. in unit test for user dao, method testRegisterUserPositive throws 
SQL exception ResultSet is TYPE_FORWARD_ONLY; when new username input is provided
[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: users.username); when existing username input is provided

    ConnectionUtil is not required for the test, the data is injected into database even sql exception is throwing out

2. Many tests in integration test planet do not run as expected, but the system test and application works fine

3. api testing for planet is not properly running, because the login step can not pass before the next request.

4. when executing celestial deleting tests, it is challenging to found a valid object each time running tests automatically

5. the step definition for different feature files could be used again, so it causes the step definition organization problem