# KGraphQL-NBA2012

NBA2012 is [Spring Boot](https://projects.spring.io/spring-boot/) demo application for [KGraphQL](https://github.com/pgutkowski/KGraphQL) library. 
It provides advanced statistics about NBA season 2011-2012.

## Demo instance

Every push to master branch is automatically deployed to heroku at at https://kql-nba2012.herokuapp.com. First request may take up to 1 minute, because Heroku stops free tier dyno after 30 mintues of inactivity.

#### example queries links:

Click on example link to see response JSON directly in your browser:

* [{player(id:34){name, team{id}}}](https://kql-nba2012.herokuapp.com/api?query=%7Bplayer(id%3A16)%7Bname%2C%20team%7Bid%7D%7D%7D)
* [{player(id:34){name, tenures{team{id}, startDate, endDate}}}](https://kql-nba2012.herokuapp.com/api?query=%7Bplayer(id%3A34)%7Bname%2C%20tenures%7Bteam%7Bid%7D%2C%20startDate%2C%20endDate%7D%7D%7D)
* [{players(name: "James"){name, minutes, offRtg, defRtg}}](https://kql-nba2012.herokuapp.com/api?query=%7B%20players(name%3A%20%22James%22)%7Bname%2C%20minutes%2C%20offRtg%2C%20defRtg%7D%20%7D)
* [{players{name, team{id}}}](https://kql-nba2012.herokuapp.com/api?query=%7Bplayers%7Bname%2C%20team%7Bid%7D%7D%7D)
* [{team(id: "MIA"){id, minutes, offRtg, defRtg, overallRtg}}](https://kql-nba2012.herokuapp.com/api?query=%7Bteam(id%3A%20%22MIA%22)%7Bid%2C%20minutes%2C%20offRtg%2C%20defRtg%2C%20overallRtg%7D%7D)
* [{games{id, homeTeam{ ...team }, awayTeam{ ...team }}} fragment team on Team { id, offRtg, defRtg }](https://kql-nba2012.herokuapp.com/api?query=%7Bgames%7Bid%2C%20homeTeam%7B%20...team%20%7D%2C%20awayTeam%7B%20...team%20%7D%7D%7D%20fragment%20team%20on%20Team%20%7B%20id%2C%20offRtg%2C%20defRtg%20%7D)
* [{tenures(playerId: 34){startDate, team{...team}}} fragment team on Team { id, offRtg, defRtg }](https://kql-nba2012.herokuapp.com/api?query=%7Btenures(playerId%3A%2034)%7BstartDate%2C%20team%7B...team%7D%7D%7D%20fragment%20team%20on%20Team%20%7B%20id%2C%20offRtg%2C%20defRtg%20%7D)
* [{__type(name: "Player"){description, fields{name, type{kind, name, ofType{name}} }} }](https://kql-nba2012.herokuapp.com/api?query=%7B%20__type(name%3A%20"Player")%7Bdescription%2C%20fields%7Bname%2C%20type%7Bkind%2C%20name%2C%20ofType%7Bname%7D%7D%20%7D%7D%20%7D)
