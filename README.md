# auction-management

The is an e-auction service. This is developed by using Java 15, spring boot microservice, MongoDB, MariaDB, RabitMQ, and Axon.
It has the following microservice:-

1. auction.user.singup to signup user to use e-auction service.
2. product.cmd.api for the CQRS patterns command
3. product.query.api for the CQRS patterns query
4. product.scheduler to run a configurable scheduler that checks the winning bid and notifies the winner by email.

The frontend is done by ReactJs 17.

Due to lack of time and there is no unit test done and no CI/CD pipeline configured. But this project fulfills all technical requirements.