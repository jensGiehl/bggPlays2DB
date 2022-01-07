# Boardgamegeek Plays to Database

Since I log every game on [Boardgamegeek](https://boardgamegeek.com/), you can generate some interesting statistics from it.

To make it easier for me to generate them, it is easy for me to have the data in a database. There you can use SQL to pull out the interesting statistics.


## How to run
A comma-separated list of [Boardgamegeek](https://boardgamegeek.com/) usernames must be passed via the [Spring configuration options](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config).

The corresponding property is called `bggusernames`.

Via the [spring.datasource](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.data.spring.datasource.url) properties can also be used to specify a persistent database.

All further info to start the application could be found in the [Spring Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.running-your-application).


## Example SQLs
```sql
select location, count(*) from play where year(play_date) = 2021 group by location order by 2 desc;

select count(*) from play where year(play_date) = 2021;

select name, count(*) from player join play on player.play_id = play.id where year(play_date) = 2021 and name not in ('Jens', 'Anonymous player') and name not like 'Bot%' group by name order by 2 desc;

select gamename, count(*) from play where year(play_date) = 2021 group by gamename order by 2 desc, gamename;

select gamename, sum(length) from play where year(play_date) = 2021 group by gamename order by 2 desc, gamename;

select gamename, count(*), sum(length) from play where year(play_date) = 2021 group by gamename order by 2 desc, gamename;

select name, gamename, count(*), sum(length) from player join play on player.play_id = play.id where year(play_date) = 2021 and name not in ('Jens', 'Anonymous player') and name not like 'Bot%' group by name, gamename order by name, 4 desc;
```

## :scream: No Tests, Dirty code, not following best practices and so on
I am aware that this software is not a masterpiece (in the sense of software craftsmanship).

But it doesn't have to be (yet). If you look at it realistically, I am probably the only user of this software (clearly, because it is also tailored to my needs). And even I will use the software only temporarily.

So why should I invest time in quality? Okay, that sounds like an excuse.... but look at the code: It's really manageable. And even if it is no longer maintainable... who cares? :broken_heart: 

That's why I rather use the time for my next project ... :smirk:


But that's not to say that anyone can steer anything to it. On the contrary!
Besides, I'm always interested in how to solve things better (even if I know that some things here are quick and especially dirty). So feel free to contact me. :email:
