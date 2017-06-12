%Keenan Parker
%1001024878
%4/21/2016
%hw11q04

clear all;
A = [64, 31,  9,  4,  3, 39, -15;
     38, 92, 26, 89, 74, 270, 218;
     81, 43, 80, 91, 50, 225, -40;
     53, 18,  3, 80, 48, 178, 183;
     35, 90, 93, 10, 90, 110, 81;
     94, 98, 73, 26, 61, 150, 25;
     88, 44, 49, 34, 62, 112, 101;
     55, 11, 58, 68, 86, 147, 170;
     62, 26, 24, 14, 81, 54, 252;
     59, 41, 46, 72, 58, 185, 94;
     21, 59, 96, 11, 18, 81, -216;
     30, 26, 55, 65, 24, 156, -69;
     47, 60, 52, 49, 89, 158, 200;
     23, 71, 23, 78,  3, 227, -57;
     84, 22, 49, 72, 49, 166, 49;
     19, 12, 62, 90, 17, 192, -118;
     23, 30, 68, 89, 98, 208, 188;
     17, 32, 40, 33, 71, 98, 164;
     23, 42, 37, 70, 50, 182, 89;
     44, 51, 99, 20, 47, 91, -109];

b = [-311, -148, -1509, 159, 451, -407, 356, 673, 1871, -335, -1761, -1416, 784, -2108, -509, -2072, 369, 856, -449, -915]';

%% Solution 1

x1 = A \ b;
disp('____________________________________________________________________________');

disp('Solution 1: A \ b = x1 = ');
disp(x1');

x1_residual = norm(b-A*x1);
fprintf('The residual of x1 = %i.\n',x1_residual);

%% Solution 2

[U,S,V] = svd(A);
size_A = size(A);
row_size_A = size_A(2);
x2 = [0;0;0;0;0;0;0];
singular_values = diag(S);
for i = 1:row_size_A
    U_col = U(:,i);
    x2 = x2 + ((U_col'*b)/singular_values(i))*V(:,i);
end
disp('____________________________________________________________________________');
disp('Solution 2: x2 = ');
disp(x2');


x2_residual = norm(b-A*x2);
fprintf('The residual of x2 = %i.\n',x2_residual);
%% Solution 3

S_effective = S >= 0.01;
S_effective_size = size(S_effective);
r = 0;
for i = 1:S_effective_size(1)
    for k = 1:S_effective_size(2)
        if(S_effective(i,k) == 1)
            r = r + 1;
        end
    end
end


x3 = [0;0;0;0;0;0;0];
for i = 1:r
    U_col = U(:,i);
    x3 = x3 + ((U_col'*b)/singular_values(i))*V(:,i);
end
disp('____________________________________________________________________________');
disp('Solution 3: x3 = ');
disp(x3');


x3_residual = norm(b-A*x3);
fprintf('The residual of x3 = %i.\n',x3_residual);
%% True Value

x = [1, -2, 3, -4, 5, -6, 7]';

disp('____________________________________________________________________________');
disp('True value: x = ');
disp(x');

x_residual = norm(b-A*x);
fprintf('The residual of x = %i.\n',x_residual);
disp('____________________________________________________________________________');
fprintf('I believe that the best solution is solution 3. \nAlthough it does not have the smallest residual it is the closest to the true value of x by far.\nThis shows that the residual is not always the best thing to rely on. \nIf it was then solution 1, which is far from what the true values are, would be the best, but it is not.\n');
