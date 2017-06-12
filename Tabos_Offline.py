#stats and explantations
#str = Strength  = increases melee damage
#int = Intelligence = increases magic damagae 
#dex = Dexterity = increases ranged damage
#vit = Vitality = increases health
import random


class human(): 
    def __init__(self):
        self.name = 'Brave Adventurer'
        self.int = 10
        self.str = 10
        self.dex = 10
        self.vit = 10
        self.health = self.vit*10
        self.level = 1
        self.weapon = 'None'
class highelf():
    def __init__(self):
        self.name = 'Brave Adventurer'
        self.int = 15
        self.str = 5
        self.dex = 5
        self.vit = 10
        self.health = self.vit*10
        self.level = 1
        self.weapon = 'None'
class woodelf():
    def __init__(self):
        self.name = 'Brave Adventurer'
        self.int = 5
        self.str = 5
        self.dex = 15
        self.vit = 10
        self.health = self.vit*10
        self.level = 1
        self.weapon = 'None'
class halfgiant():
    def __init__(self):
        self.name = 'Brave Adventurer'
        self.int = 5
        self.str = 15
        self.dex = 5
        self.vit = 10
        self.health = self.vit*10
        self.level = 1
        self.weapon = 'None'
r = '' #holder for race
rc = 1 #race count
player = 'plaza'
print('************ Welcome to Tabos Offline ************','\n')
name = input('Enter a name adventurer: ')
print('\n','Username',name,'accepted','\n')
race = input('Races: \n Human(h) \n High Elf(e) \n Wood Elf(w) \n Half-Giant(g): \n Please select a race: ')
while rc == 1:
    if race == 'h' or 'e' or 'w' or 'g':
        if race == 'h':
            human.name = name
            race = human()
            r = 'Human'
        if race == 'e':
            highelf.name = name
            race = highelf()
            r = 'High Elf'
        if race == 'w':
            woodelf.name = name
            race = woodelf()
            r = 'Wood Elf'
        if race == 'g':
            halfgiant.name = name
            race = halfgiant()
            r = 'Half-Giant'
        rc -= 1
    else:
        race = input('Races: \n Human(h) \n High Elf(e) \n Wood Elf(w) \n Half-Giant(g): \n Please select a race: ')

gold = 10
sp = 0
exp = 10*race.level + 5
weaponinfo = 'None'
vlc = 0 #village leader count - used to make conversation easier
we = 0#weapon effect
se = 0 #sword effect
me = 0#magic effect
be = 0#bow effect
playerpos = 0#player position
mosterpos = 0#
sword = 'none'
bow = 'none'
staff = 'none'
dungeon = 'unactive' #if active then you are in dungeon
battle = 'unactive' # if active battle occurs

### FOREST MONSTERS ###
class alphawolf():
    def __init__(self):
        self.name = 'Alpha Wolf'
        self.health = (random.randint(13,16) + race.level) *10
        self.damage = random.randint(15,18) + race.level
        self.exp = random.randint(10,14)
        self.gold = random.randint(10,18)
class wolf():
    def __init__(self):
        self.name = 'Wolf'
        self.health = (random.randint(5,10) + race.level) *10
        self.damage = random.randint(10,15) + race.level
        self.exp = random.randint(4,6)
        self.gold = random.randint(0,8)
class goblin():
    def __init__(self):
        self.name = 'Goblin'
        self.health = (random.randint(4,8) + race.level) *10
        self.damage = random.randint(9,13) + race.level
        self.exp = random.randint(3,5)
        self.gold = random.randint(0,5)
class goblinking():
    def __init__(self):
        self.name = 'Goblin King'
        self.health = (random.randint(11,13) + race.level) *10
        self.damage = random.randint(11,13) + race.level
        self.exp = random.randint(9,14)
        self.gold = random.randint(10,15)
class ent():
    def __init__(self):
        self.name = 'Ent'
        self.health = (random.randint(15,18) + race.level) *10
        self.damage = random.randint(6,10) + race.level
        self.exp = random.randint(6,9)
        self.gold = random.randint(0,3)
###   CAVE MONSTERS ###
class bat():
    def __init__(self):
        self.name = 'Large Bat'
        self.health = (random.randint(12,16) + race.level) *10
        self.damage = random.randint(10,12) + race.level
        self.exp = random.randint(8,11)
        self.gold = random.randint(0,8)
class spider():
    def __init__(self):
        self.name = 'Cave Spider'
        self.health = (random.randint(10,13) + race.level) *10
        self.damage = random.randint(11,15) + race.level
        self.exp = random.randint(9,15)
        self.gold = random.randint(10,16)
class golem():
    def __init__(self):
        self.name = 'Golem'
        self.health = (random.randint(13,18) + race.level) *10
        self.damage = random.randint(11,11) + race.level
        self.exp = random.randint(13,18)
        self.gold = random.randint(15,25)
class dragon():
    def __init__(self):
        self.name = 'Young Dragon'
        self.health = (random.randint(18,22) + race.level) *10
        self.damage = random.randint(16,24) + race.level
        self.exp = random.randint(20,21)
        self.gold = random.randint(100,1005)

print('\nGenerating Character...Done')
print('\n','Name:',name,'  ','Race:',r,'\n','Health:',race.health,'   Level:',race.level,'\n','Strength:',race.str,'Dexterity:',race.dex,'\n','Intelligence:',race.int,'Vitality:',race.vit,'\n Gold:',gold,'   ','SP:',sp,'\n EXP to next Level:',exp,'\n Weapon:',race.weapon,' Weapon Info:',weaponinfo)
print('\nYour Starting Location will be: The Town of Beginings')
print('\nHave a Grand adventure and...DON\'T DIE \n\n\n\n\n\n\n')
print('\nYou wake up and find yourself in a open plaza, near a fountain. \nYou are not quite sure how you got there but... \noh well. You are an adventurer so...this must be an adventure \nYou see a sign that has \'The Town of Beginings\' in big letters. \nAs you look around you see it is a small and humble village. \nSince you have nothing else to do you began your adventure','\n')
print('\n(Note: To select an option enter the letter next to it)')
location = 'town'
while race.health > 0:
        while location == 'town':
            if exp <= 0:
                race.level += 1
                exp = 10*race.level + 5
                print('\nCongratgulations! You are now level',race.level,'!!!')
                sp += 2 + race.level
                print('You now have',sp,'SP')
            if race.health <= 0:
                    print('Game Over')
                    break
            if player == 'plaza':
                if race.health < race.vit * 10:
                    print('\n***Health has been restored***')
                    race.health = race.vit * 10
                print('\nYou are now in the plaza. From the plaza there are 4 places you can go. \n a) The Town Hall - (Recommended for first time players) \n b) The Shop \n c) The Forest - (Recommended Adventures for level 1-3) \n d) The Local Cave - (Recommended for Adventures level 5+)')
                player = input('Where do you wish to go: ')
                if player == 'a':
                    player = 'hall'
                if player == 'b':
                    player = 'shop'
                if player == 'c':
                    player = 'forestentrance'
                if player == 'd':
                    player = 'cave'
            if player == 'hall':
                print('\nYou walk to and enter the Town Hall, The biggest building in the town.\nThe Town Hall is the main hub of the town and has many uses.\nYou can find the Village leader and Stats board here.\n\nOptions:\n a) Talk to Village leader - (Has information useful for first time players) \n b) Look at the Stats Board \n c) Return to Plaza')
                player = input('What do you want to do: ' )
                if player == 'a':
                    vlc += 1
                    print('\nYou walk up to the Village leader to talk He sees you and says \n  \n\'Ah',name,'what could i do for you?\' \n 1) What are Stats? \n 2) What is in the Forest? \n 3) What is in the Cave? \n 4) Return to the entrance')
                    while vlc > 0:
                        player = input('What do you want to ask/do: ')
                        if player == '1':
                            print('\n','Stats are an numeric representation of you certain stat. There are 4 main stats: str, dex, int, and vit. \n str = Strength  = increases melee damage \n int = Intelligence = increases magic damagae \n dex = Dexterity = increases ranged damage \n vit = Vitality = increases health \n As you level up you will get sp (stat points) \n You can use these to increase your stats at the Stats Board \n')
                            print('\'',name,'is there anything else I could do for you?\' \n 2) What is in the Forest? \n 3) What is in the Cave? \n 4) Return to the entrance')
                        if player == '2':
                            print('\n','The forest contains many monsters that keep to themselves. They will attack you if they run into you in the forest though. It is a good spot to train for adventurers level 1-5.\nThere are various traps in the forest so watchout. Also try not to get lost.')
                            print('\n','\'',name,'is there anything else I could do for you?\' \n 1) What are Stats? \n 3) What is in the Cave? \n 4) Return to the entrance')
                        if player == '3':
                            print('\n','The cave contains many stronger monsters. They do not leave the cave thoguh. Only the strongest can explorer there.\nBeware of traps and getting lost.\nTheir is also a legend of a dragon once living in the cave though no one has ever confirmed it.')
                            print('\'',name,'is there anything else I could do for you?\' \n 1) What are Stats? \n 2) What is in the Forest? \n 4) Return to the entrance')
                        if player == '4':
                            print('Have nothing else to ask the Village Leader you go on you way')
                            player = 'hall'
                            vlc -= 1
                if player == 'b':
                    while sp > 0:
                        print('\nYou have',sp,'SP at the moment')
                        statup = input('\n Do you wish to spend your SP(s) at the moment? (y or n): ')
                        if statup == 'y':
                            while sp > 0:
                                spu = input('\n Stats: \n a)str \n b)int \n c)dex \n d)vit \n What stat do you wish to increase: ')
                                if spu == 'a':
                                    stru = int(input('\n By how much do you want to increase your strength? \n Enter SP amount: '))
                                    if stru > sp:
                                        print('Invalid try again \n')
                                        stru = int(input('By how much do you want to increase your Strength? \n Enter SP amount: '))
                                    else:
                                        race.str += stru
                                        sp -= stru
                                if spu == 'b':
                                    intu = int(input('\n By how much do you want to increase your Intelligence? \n Enter SP amount: '))
                                    if intu > sp:
                                        print('Invalid try again \n')
                                        stru = int(input('By how much do you want to increase your Intelligence? \n Enter SP amount: '))
                                    else:
                                        race.int += intu
                                        sp -= intu
                                if spu == 'c':
                                    dexu = int(input('\n By how much do you want to increase your Dexterity? \n Enter SP amount: '))
                                    if dexu > sp:
                                        print('Invalid try again \n')
                                        dexu = int(input('By how much do you want to increase your Dexterity? \n Enter SP amount: '))
                                    else:
                                        race.dex += dexu
                                        sp -= dexu
                                if spu == 'd':
                                    vitu = int(input('\n By how much do you want to increase your Vitality? \n Enter SP amount: '))
                                    if vitu > sp:
                                        print('Invalid try again \n')
                                        vitu = int(input('By how much do you want to increase your Vitality? \n Enter SP amount: '))
                                    else:
                                        race.vit += vitu
                                        race.health = race.vit*10
                                        sp -= vitu
                        else:
                            print('\n','After admiring your awesomeness for awhile you return to the entrance')
                            player = 'hall'
                    print('\n','Name:',name,'  ','Race:',r,'\n','Health:',race.health,'   Level:',race.level,'\n','Strength:',race.str,'(+',se,'str)','Dexterity:',race.dex,'(+',be,'dex)','\n','Intelligence:',race.int,'(+',me,'int)','Vitality:',race.vit,'\n Gold:',gold,'   ','SP:',sp,'\n EXP to next Level:',exp,'\n Weapon:',race.weapon,'\n Weapon Info:',weaponinfo)
                    print('\n','After admiring your awesomeness for awhile you return to the entrance')
                    player = 'hall'
                if player == 'c':
                    print('\nYou have finished your bussiness ar the Town Hall and you return to the Plaza')
                    player = 'plaza'
            if player == 'shop':
                if race.level < 3:
                    sword,swordcost,we = 'Short Iron Sword',10,2
                    staff,staffcost,we = 'Wooden Staff',10,2
                    bow,bowcost,we = 'Short Bow',10,2
                if race.level == 3:
                    sword,swordcost,we = 'Refined Iron Sword',30,3.5
                    staff,staffcost,we = 'Enchanted Staff',30,3.5
                    bow,bowcost,we = 'Long Bow',30,3.5
                if race.level == 5:
                    sword,swordcost,we = 'Steel Sword',50,5
                    staff,staffcost,we = 'Grand Enchanted Staff',50,5
                    bow,bowcost,we = 'Hunter\'s Bow',50,5
                print(' \n ShopKeep: \'Welcome to the shop. What can I do for you?\' \n \n Purchasable Items: \n a)',sword,'Cost:',swordcost,'Gold  ','(+',we,'str)','\n b)',staff,'Cost:', staffcost,'Gold  ','(+',we,'int)','\n c)',bow,'Cost:',bowcost,'Gold  ','(+',we,'dex)','\n d)Leave Shop')
                buy = input('What do you want to buy: ')
                if buy == 'a':
                    be,se,me = 0,0,0
                    if gold < swordcost:
                        print('\nShopKeep: \'Sorry but you don\'t have enough gold\'')
                        continue
                    else:
                        if race.level < 3:
                            race.weapon = sword
                            weaponinfo = 'A sturdy but short sword.\n Useful for your beginner swordsman. \n Increases str by 2 when equipped'
                            gold -= swordcost
                            se += we
                        if race.level == 3:
                            se = 0
                            race.weapon = sword
                            weaponinfo = 'A standered sword made of a high purity iron.\n Useful for your average swordsman. \n Increases str by 3.5 when equipped'
                            gold -= swordcost
                            se += we
                        if race.level == 5:
                            se = 0
                            race.weapon = sword
                            weaponinfo = 'A steel sword that every swordsman yearns for. \n Useful for a expert swordsman. \n Increases str by 5 when equipped'
                            gold -= swordcost
                            se += we
                        print('\nShopKeep: \'Thanks for the Purchase, See you around\'')
                        player = 'plaza'
                if buy == 'b':
                    be,se,me = 0,0,0
                    if gold < staffcost:
                        print('\nShopKeep: \'Sorry but you don\'t have enough gold\'')
                        continue
                    else:
                        if race.level < 3:
                            race.weapon = staff
                            weaponinfo = 'A regular wooden stick that has been reiforced with magic. \n It is a useful conductor for beginner mages. \n Increases int by 2 when equipped'
                            gold -= staffcost
                            me += we
                        if race.level == 3:
                            me = 0
                            race.weapon = staff
                            weaponinfo = 'A high quality wooden stick that has been reinforced with quality magic. \n Useful for adept mages. \n Increases int by 3.5 when equppied'
                            gold -= staffcost
                            me += we
                        if race.level == 5:
                            me = 0
                            race.weapon = staff
                            weaponinfo = 'A grand staff reinforced by a master\'s magic. \n Useful for expert mages. Increases int by 5'
                            gold -= staffcost
                            me += we
                        print('\nShopKeep: \'Thanks for the Purchase, See you around\'')
                        player = 'plaza'
                if buy == 'c':
                    be,se,me = 0,0,0
                    if gold < bowcost:
                        print('\nShopKeep: \'Sorry but you don\'t have enough gold\'')
                        continue
                    else:
                        if race.level < 3:
                            race.weapon = bow
                            weaponinfo = 'A simple short bow that has a strong string. \n Useful for beginner archers. Increases dex by 2'
                            gold -= bowcost
                            be += we
                        if race.level == 3:
                            be = 0
                            race.weapon = bow
                            weaponinfo = 'A longbow that can shoot hit a target miles away. \n Useful for your average archer. Increases dex by 3.5'
                            gold -= bowcost
                            be += we
                        if race.level == 5:
                            be = 0
                            race.weapon = bow
                            weaponinfo = 'A bow for an elite hunter. \n Useful for an expert archer. Increaes dex by 5'
                            gold -= bowcost
                            be += we
                        print('\nShopKeep: \'Thanks for the Purchase, See you around\'')
                        player = 'plaza'
                if buy == 'd':
                    print('\nShopKeep: \'Thanks for stopping bye!\' ')
                    print('\n\nYou realize you came to the Shop for no reason and head back the the Plaza')
                    player = 'plaza'
            if player == 'forestentrance':
                print('\nEntering the forest')
                forestset = [ [0,0,0,0,0],
                              [0,0,0,0,0],
                              [0,0,0,0,0],
                              [0,0,0,0,0],
                              [0,0,0,0,0] ]
                forestprint = [ ['.','.','.','.','.'],
                              ['.','.','.','.','.'],
                              ['.','.','.','.','.'],
                              ['.','.','.','.','.'],
                              ['.','.','.','.','.'] ]
                for rownum in range(0,len(forestprint)):
                    for colnum in range(0,len(forestprint)):
                        forestprint[rownum][colnum] = 'O'
                    forestprint[4][2] = 'X'
                print('\n','The Forest is a 5x5 area. Good luck in you\'re adventure')
                for row in range(0,len(forestprint)):
                        print(forestprint[row])
                for row in range(0,5):
                    for col in range(0,5):
                        areagen = random.randint(1,100)
                        if areagen <= 30:
                            forestset[row][col] = 0
                        if 30 < areagen <= 70:
                            forestset[row][col] = 1
                        if 70 < areagen <= 85:
                            forestset[row][col] = 2
                        if 85 < areagen <= 100:
                            forestset[row][col] = 3
                forestset[4][2] = 0
                row = 4
                col = 2
                playerpos = forestset[4][2]
                dungeon = 'active'
                while dungeon == 'active':
                    if race.health <= 0:
                        print('\nGame Over')
                        break
                    while battle == 'active':
                        print('\nYou have encountered a',monster.name)
                        while monster.health > 0:
                            if race.health <= 0:
                                print('\nGame Over')
                                break
                            print('\n',monster.name,' Health:',monster.health,'  Your Current Health:',race.health)
                            battlemove = input('\nAvaliable action:\n\n p)Melee Attack       m)Magic Attack \n\n r)Ranged Attack       e)Escape \n\nPick an action: ')
                            if battlemove == 'p':
                                if race.weapon == sword:
                                    damage = race.str + se
                                    print('You swing your',race.weapon,'and deal',damage,'damage')
                                    monster.health -= damage
                                else:
                                    damage = race.str
                                    print('You yell \'FALCON PUNCH\' and hit the',monster.name,'dealing',damage,'damage')
                                    monster.health -= damage
                            if battlemove == 'm':
                                if race.weapon == staff:
                                    damage = race.int + me
                                    print('You use your',race.weapon,'and cast a spell dealing',damage,'damage')
                                    monster.health -= damage
                                else:
                                    damage = race.int
                                    print('You yell \'FIRE BALL\' and noting happens. The',monster.name,'loses',damage,'health from laughing at you')
                                    monster.health -= damage
                            if battlemove == 'r':
                                if race.weapon == bow:
                                    damage = race.dex + be
                                    print('You shoot an arrow from your',race.weapon,'and deal',damage,'damage')
                                    monster.health -= damage
                                else:
                                    damage = race.dex
                                    print('You throw a rock and hit the',monster.name,'dealing',damage,'damage')
                                    monster.health -= damage
                            if battlemove == 'e':
                                goldloss = random.randint(1,6+race.level)
                                print('You run away like a coward. While running you drop a small amount of gold. You lose',goldloss,'gold')
                                gold -= goldloss
                                battle = 'unactive'
                                break
                            if monster.health > 0:
                                race.health -= monster.damage
                                print('\nThe',monster.name,'attacks you and deals',monster.damage,'damage')
                        if battlemove == 'e':
                            break
                        else:
                            print('Victory you gain',monster.exp,'experince and',monster.gold,'gold')
                            exp -= monster.exp
                            gold += monster.gold
                            battle = 'unactive'
                    if race.health <= 0:
                        print('GAME OVER')
                        break
                    if row == 4 and col == 2:
                        #for row in range(0,5):
                            #print(forestset[row])
                        print('\nYou find yourself at the entracne to the forest and proceed in.')
                        forestmove = input('\nFrom the your current postion you can move: \n n)North \n e)East \n w)West \n r)Return to town \nWhat do you wish to do: ')
                        if forestmove == 'n':
                                row -= 1 
                        if forestmove == 'e':
                                col += 1 
                        if forestmove == 'w':
                                col -= 1 
                        if forestmove == 'r' and playerpos == forestset[4][2]:
                            print('\nYou exit the forest and return to town')
                            player = 'plaza'
                            dungeon = 'unactive'
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                        #dungeon = 'unactive'
                    elif (row == 0 and col == 1) or (row == 0 and col == 2) or (row == 0 and col == 3):
                        forestmove = input('\nFrom the your current postion you can move: \n s)South \n e)East \n w)West \nWhat do you wish to do: ')
                        if forestmove == 's':
                                row += 1
                        if forestmove == 'e':
                                col += 1
                        if forestmove == 'w':
                                col -= 1
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 4 and col == 1) or (row == 4 and col == 3):
                        forestmove = input('\nFrom the your current postion you can move: \n n)North \n e)East \n w)West \nWhat do you wish to do: ')
                        if forestmove == 'n':
                                row -= 1 
                        if forestmove == 'e':
                                col += 1 
                        if forestmove == 'w':
                                col -= 1 
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (col == 0 and row == 1) or (col == 0 and row == 2) or (col == 0 and row == 3):
                        forestmove = input('\nFrom the your current postion you can move: n)North \n e)East \n s)South \nWhat do you wish to do: ')
                        if forestmove == 's':
                                row += 1 
                        if forestmove == 'e':
                                col += 1 
                        if forestmove == 'n':
                                row -= 1
                        if forestmove == 'w':
                            print('Invalid')
                            forestmove = input('\nFrom the your current postion you can move: n)North \n e)East \n s)South \nWhat do you wish to do: ')     
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (col == 4 and row == 1) or (col == 4 and row == 2) or (col == 4 and row == 3):
                        forestmove = input('\nFrom the your current postion you can move: \n n)North \n w)West \n s)South \nWhat do you wish to do: ')
                        if forestmove == 's':
                                row += 1 
                        if forestmove == 'w':
                                col -= 1 
                        if forestmove == 'n':
                                row -= 1 
                        if forestset[row][col] == 1:
                            randboss = random.randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 0 and col == 0):
                        forestmove = input('\nFrom the your current postion you can move: \n s)South \n e)East \nWhat do you wish to do: ')
                        if forestmove == 's':
                                row += 1 
                        if forestmove == 'e':
                                col += 1 
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                    elif (row == 0 and col == 4):
                        forestmove = input('\nFrom the your current postion you can move: \n w)West \n s)South \nWhat do you wish to do: ')
                        if forestmove == 's':
                                row += 1 
                        if forestmove == 'w':
                                col -= 1 
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 4 and col == 0):
                        forestmove = input('\nFrom the your current postion you can move: \n n)North \n e)East \nWhat do you wish to do: ')
                        if forestmove == 'n':
                                row -= 1 
                        if forestmove == 'e':
                                col += 1 
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 4 and col == 4):
                        forestmove = input('\nFrom the your current postion you can move: \n n)North \n w)West \nWhat do you wish to do: ')
                        if forestmove == 'n':
                                row -= 1 
                        if forestmove == 'w':
                                col -= 1 
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    else:
                        forestmove = input('\nFrom the your current postion you can move: \n n)North \n e)East \n w)West \n s)South \nWhat do you wish to do: ')
                        if forestmove == 'n':
                                row -= 1 
                        if forestmove == 's':
                                row += 1 
                        if forestmove == 'e':
                                col += 1 
                        if forestmove == 'w':
                                col -= 1
                        if forestset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if forestset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou tripped over a rock')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour leg gets caught in an old trap forgtton by a Hunter')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nYou fall into a mysterious hole.............who digs these things?')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if forestset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                player = 'plaza'
                        
            if player == 'cave':
                print('\nEntering the Cave')
                caveset =   [ [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0] ]
                caveprint = [ ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'],
                                ['.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'] ]
                for rownum in range(0,len(caveprint)):
                    for colnum in range(0,len(caveprint)):
                        caveprint[rownum][colnum] = 'O'
                    caveprint[14][7] = 'X'
                print('\n','The Cave is a 15x15 area. Good luck in you\'re adventure')
                for row in range(0,len(caveprint)):
                        print(caveprint[row])
                for row in range(0,len(caveset)):
                    for col in range(0,len(caveset)):
                        areagen = random.randint(1,100)
                        if areagen <= 30:
                            caveset[row][col] = 0
                        if 30 < areagen <= 70:
                            caveset[row][col] = 1
                        if 70 < areagen <= 85:
                            caveset[row][col] = 2
                        if 85 < areagen <= 100:
                            caveset[row][col] = 3
                caveset[4][2] = 0
                row = 14
                col = 7
                playerpos = caveset[14][7]
                dungeon = 'active'
                while dungeon == 'active':
                    if race.health <= 0:
                        print('\nGame Over')
                        break
                    while battle == 'active':
                        print('\nYou have encountered a',monster.name)
                        while monster.health > 0:
                            if race.health <= 0:
                                print('\nGame Over')
                                break
                            print('\n',monster.name,' Health:',monster.health,'  Your Current Health:',race.health)
                            battlemove = input('\nAvaliable action:\n\n p)Melee Attack       m)Magic Attack \n\n r)Ranged Attack       e)Escape \n\nPick an action: ')
                            if battlemove == 'p':
                                if race.weapon == sword:
                                    damage = race.str + se
                                    print('You swing your',race.weapon,'and deal',damage,'damage')
                                    monster.health -= damage
                                else:
                                    damage = race.str
                                    print('You yell \'FALCON PUNCH\' and hit the',monster.name,'dealing',damage,'damage')
                                    monster.health -= damage
                            if battlemove == 'm':
                                if race.weapon == staff:
                                    damage = race.int + me
                                    print('You use your',race.weapon,'and cast a spell dealing',damage,'damage')
                                    monster.health -= damage
                                else:
                                    damage = race.int
                                    print('You yell \'FIRE BALL\' and noting happens. The',monster.name,'loses',damage,'health from laughing at you')
                                    monster.health -= damage
                            if battlemove == 'r':
                                if race.weapon == bow:
                                    damage = race.dex + be
                                    print('You shoot an arrow from your',race.weapon,'and deal',damage,'damage')
                                    monster.health -= damage
                                else:
                                    damage = race.dex
                                    print('You throw a rock and hit the',monster.name,'dealing',damage,'damage')
                                    monster.health -= damage
                            if battlemove == 'e':
                                goldloss = random.randint(1,6+race.level)
                                print('You run away like a coward. While running you drop a small amount of gold. You lose',goldloss,'gold')
                                gold -= goldloss
                                battle = 'unactive'
                                break
                            if monster.health > 0:
                                race.health -= monster.damage
                                print('\nThe',monster.name,'attacks you and deals',monster.damage,'damage')
                        if battlemove == 'e':
                            battle = 'unactive'
                            break
                        else:
                            print('Victory you gain',monster.exp,'experince and',monster.gold,'gold')
                            exp -= monster.exp
                            gold += monster.gold
                            battle = 'unactive'
                    if race.health <= 0:
                        print('GAME OVER')
                        break
                    if row == 14 and col == 7:
                        #for row in range(0,5):
                            #print(caveset[row])
                        print('\nYou find yourself at the entracne to the cave and proceed in.')
                        cavemove = input('\nFrom the your current postion you can move: \n n)North \n e)East \n w)West \n r)Return to town \nWhat do you wish to do: ')
                        if cavemove == 'n':
                                row -= 1 
                        if cavemove == 'e':
                                col += 1 
                        if cavemove == 'w':
                                col -= 1 
                        if cavemove == 'r' and playerpos == caveset[14][7]:
                            print('\nYou exit the cave and return to town')
                            player = 'plaza'
                            battle = 'unactive'
                            dungeon = 'unactive'
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                        #dungeon = 'unactive'
                    elif (row == 0 and col == 1) or (row == 0 and col == 2) or (row == 0 and col == 3) or (row == 0 and col == 4) or (row == 0 and col == 5) or (row == 0 and col == 6) or (row == 0 and col == 7) or (row == 0 and col == 8) or (row == 0 and col == 9) or (row == 0 and col == 10) or (row == 0 and col == 11) or (row == 0 and col == 12) or (row == 0 and col == 13):
                        cavemove = input('\nFrom the your current postion you can move: \n s)South \n e)East \n w)West \nWhat do you wish to do: ')
                        if cavemove == 's':
                                row += 1
                        if cavemove == 'e':
                                col += 1
                        if cavemove == 'w':
                                col -= 1
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 4 and col == 1) or (row == 4 and col == 2) or (row == 4 and col == 3) or (row == 4 and col == 4) or (row == 4 and col == 5) or (row == 4 and col == 6) or (row == 4 and col == 8) or (row == 4 and col == 9) or (row == 4 and col == 10) or (row == 4 and col == 11) or (row == 4 and col == 12) or (row == 4 and col == 13):
                        cavemove = input('\nFrom the your current postion you can move: \n n)North \n e)East \n w)West \nWhat do you wish to do: ')
                        if cavemove == 'n':
                                row -= 1 
                        if cavemove == 'e':
                                col += 1 
                        if cavemove == 'w':
                                col -= 1 
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(1,2)
                                if bossmon == 1:
                                    monster = goblinking()
                                    battle = 'active'
                                if bossmon == 2:
                                    monster = alphawolf()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = ent()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = wolf()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = goblin()
                                    battle = 'active'
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (col == 0 and row == 1) or (col == 0 and row == 2) or (col == 0 and row == 3) or (col == 0 and row == 4) or (col == 0 and row == 5) or (col == 0 and row == 6) or (col == 0 and row == 7) or (col == 0 and row == 8) or (col == 0 and row == 9) or (col == 0 and row == 10) or (col == 0 and row == 11) or (col == 0 and row == 12) or (col == 0 and row == 13):
                        cavemove = input('\nFrom the your current postion you can move: n)North \n e)East \n s)South \nWhat do you wish to do: ')
                        if cavemove == 's':
                                row += 1 
                        if cavemove == 'e':
                                col += 1 
                        if cavemove == 'n':
                                row -= 1
                        if cavemove == 'w':
                            print('Invalid')
                            cavemove = input('\nFrom the your current postion you can move: n)North \n e)East \n s)South \nWhat do you wish to do: ')     
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (col == 4 and row == 1) or (col == 4 and row == 2) or (col == 4 and row == 3) or (col == 4 and row == 4) or (col == 4 and row == 5) or (col == 4 and row == 6) or (col == 4 and row == 7) or (col == 4 and row == 8) or (col == 4 and row == 9) or (col == 4 and row == 10) or (col == 4 and row == 11) or (col == 4 and row == 12) or (col == 4 and row == 13):
                        cavemove = input('\nFrom the your current postion you can move: \n n)North \n w)West \n s)South \nWhat do you wish to do: ')
                        if cavemove == 's':
                                row += 1 
                        if cavemove == 'w':
                                col -= 1 
                        if cavemove == 'n':
                                row -= 1 
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 0 and col == 0):
                        cavemove = input('\nFrom the your current postion you can move: \n s)South \n e)East \nWhat do you wish to do: ')
                        if cavemove == 's':
                                row += 1 
                        if cavemove == 'e':
                                col += 1 
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 0 and col == 14):
                        cavemove = input('\nFrom the your current postion you can move: \n w)West \n s)South \nWhat do you wish to do: ')
                        if cavemove == 's':
                                row += 1 
                        if cavemove == 'w':
                                col -= 1 
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 14 and col == 0):
                        cavemove = input('\nFrom the your current postion you can move: \n n)North \n e)East \nWhat do you wish to do: ')
                        if cavemove == 'n':
                                row -= 1 
                        if cavemove == 'e':
                                col += 1 
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    elif (row == 14 and col == 14):
                        cavemove = input('\nFrom the your current postion you can move: \n n)North \n w)West \nWhat do you wish to do: ')
                        if cavemove == 'n':
                                row -= 1 
                        if cavemove == 'w':
                                col -= 1 
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                            
                    else:
                        cavemove = input('\nFrom the your current postion you can move: \n n)North \n e)East \n w)West \n s)South \nWhat do you wish to do: ')
                        if cavemove == 'n':
                                row -= 1 
                        if cavemove == 's':
                                row += 1 
                        if cavemove == 'e':
                                col += 1 
                        if cavemove == 'w':
                                col -= 1
                        if caveset[row][col] == 1:
                            randboss = random. randint(1,200)
                            if 0 < randboss <= 5:
                                bossmon = random.randint(0,1)
                                if bossmon == 1:
                                    monster = dragon()
                                    battle = 'active'
                            if randboss > 5:
                                randmon = random.randint(1,3)
                                if randmon == 1:
                                    monster = spider()
                                    battle = 'active'
                                if randmon == 2:
                                    monster = bat()
                                    battle = 'active'
                                if randmon ==3:
                                    monster = golem()
                                    battle = 'active'
                        if caveset[row][col] == 2:
                            trap = random.randint(1,3)
                            if trap == 1:
                                print('\nYou fell back and hurt yourself after a stalagtite fell down next to you')
                                trapdamage = random.randint(1,8)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap == 2:
                                print('\nYour foot gets caught on a pointy rock')
                                trapdamage = random.randint(6,12)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                            if trap ==3:
                                print('\nA giant boulder came out of nowhere and ran over your big toe')
                                trapdamage = random.randint(8,14)
                                race.health -= trapdamage
                                print('\nYou lost',trapdamage,'health \nCurrent Health:',race.health)
                        if caveset[row][col] == 3:
                            print('\nYou found a resting spot and take a short break.\n\nYou recover all of your health')
                            race.health = race.vit*10
                player = 'plaza'
print('You have died. GAME OVER')
                #dec. 8 - finals day[1, 2, 0, 2, 0]
[1, 2, 0, 2, 0]
[0, 0, 0, 3, 1]
[1, 1, 1, 0, 1]
[3, 2, 2, 1, 0]
[3, 1, 0, 2, 0]










                
                            


