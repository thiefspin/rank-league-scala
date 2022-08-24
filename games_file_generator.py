import random


class Team:

    def __init__(self, name: str, score: int):
        self.name = name
        self.score = score


class Game:

    def __init__(self, team_a: Team, team_b: Team):
        self.team_a = team_a
        self.team_b = team_b


team_names = ['TeamA', 'TeamB', 'TeamC', 'TeamD', 'TeamE', 'TeamF', 'TeamG', 'TeamH']
games = []


def filter_out_current_team(name: str):
    other_teams = list(team_names)
    other_teams.remove(name)
    return other_teams


for team_name in team_names:
    the_other_teams = filter_out_current_team(team_name)
    for other_team in the_other_teams:
        this_team = Team(team_name, random.randint(0, 9))
        the_other_team = Team(other_team, random.randint(0, 9))
        game = Game(this_team, the_other_team)
        games.append(game)

with open('game_file.txt', 'w') as f:
    for game in games:
        line = game.team_a.name + ' ' + str(game.team_a.score) + ', ' + game.team_b.name + ' ' + str(game.team_b.score)
        f.write(line)
        f.write('\n')
    f.close()
