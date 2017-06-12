%% Data
% c = laundry (clothes)
% d = dishwasher
% s = shower
% y = sprinklers (yard)
%c  d  s  y  usage (in gallons)
 A = [1, 0, 1, 1
      0, 0, 2, 0
      0, 1, 1, 0
      0, 0, 1, 0
      0, 1, 2, 1
      0, 0, 2, 0
      1, 0, 2, 0
      1, 1, 2, 1
      0, 1, 1, 0
      0, 1, 1, 0
      0, 0, 2, 1
      0, 0, 2, 0
      2, 1, 2, 0
      0, 1, 1, 1
      0, 0, 1, 0
      0, 0, 1, 0
      0, 0, 2, 1
      1, 1, 1, 0
      0, 0, 2, 0
      1, 0, 1, 1
      0, 1, 1, 0
      1, 1, 1, 0
      1, 0, 2, 1
      0, 0, 2, 0
      0, 0, 1, 0
      0, 1, 1, 0
      0, 0, 1, 0
      1, 0, 2, 0
      1, 1, 1, 0
      0, 0, 2, 0];
  b = [841
       9
      15
       6
      838
       11
       37
      859
     13
      10
      805
      11
      65
      811
        9
       7
       792
       37
        9
      922
        15
       65
       858
        6
       11
      18
      11
       38
       36
       9];

x = A\b;
%% Results
disp('   Laundry  Dishwasher  Shower  Sprinkler');
disp(x');
disp('water in gallons')