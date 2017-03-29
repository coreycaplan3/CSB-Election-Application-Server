DATABASE SCHEMA - SQL
_User (email, NAME, user_id)
position_model (running_position)
Candidate (candidate_id, election_year, POSITION;
FK candidate_id _user(user_id), FK POSITION position_model ( POSITION ))

Votes (user_id, candidate_id, POSITION, election_year;
FK user_id _user(user_id) )
UNIQUE INDEX user_id, POSITION, election_year

CREATE TABLE IF NOT EXISTS _user (
  user_id VARCHAR(255) PRIMARY KEY,
  name    TEXT,
  email   VARCHAR(1024)
);

CREATE TABLE IF NOT EXISTS position_model (
  running_position             VARCHAR(255) PRIMARY KEY,
  running_position_description TEXT
);

CREATE TABLE IF NOT EXISTS election (
  election_id      SERIAL PRIMARY KEY,
  is_election_over BOOLEAN
);

CREATE TABLE IF NOT EXISTS candidate (
  candidate_id     VARCHAR(255) PRIMARY KEY,
  election_id      SERIAL,
  running_position VARCHAR(255),
  FOREIGN KEY (election_id) REFERENCES election (election_id),
  FOREIGN KEY (running_position) REFERENCES position_model (running_position)
);

CREATE TABLE IF NOT EXISTS votes (
  user_id          VARCHAR(255),
  candidate_id     VARCHAR(255),
  running_position VARCHAR(255),
  election_id      SERIAL,
  FOREIGN KEY (election_id) REFERENCES election (election_id),
  FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
  FOREIGN KEY (running_position) REFERENCES position_model (running_position),
  PRIMARY KEY (user_id, running_position, election_id) -- the user can only vote on one running position per election
);

CREATE TABLE IF NOT EXISTS special_vote_code (
  election_id   SERIAL PRIMARY KEY,
  election_code VARCHAR(255),
  FOREIGN KEY (election_id) REFERENCES election (election_id)
)