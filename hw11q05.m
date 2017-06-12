
%Keenan Parker
%1001024878
%4/21/2016
%hw11q05

clear all;

p1 = [ 0 1 0; 1 1 1; 0 1 0];
p2 = [ 0 1 0; 1 1 1; 0 1 0];
p3 = [ 0 1 0; 1 1 1; 1 0 0];
P = [p1 ;p2 ;p3];

x1 = [ 1 0 1; 0 1 0; 1 0 1];
x2 = [ 1 0 0; 0 1 0; 1 0 1];
x3 = [ 1 0 1; 0 1 0; 1 0 1];
X = [x1 ;x2 ;x3];

[Up,Sp,Vp] = svd(P);
[Ux,Sx,Vx] = svd(X);

z1 = [ 0 1 1 1 1 1 0 1 0]';
z2 = [ 1 0 1 0 1 0 1 1 1]'; 

I = eye(9);

k = 2;
for i = 1:k
    Upk(:,i) = Up(:,i);
    Uxk(:,i) = Ux(:,i);
end
disp('____________________________________________________________________________');

disp('For P Uk = ');
disp(Upk);
disp('____________________________________________________________________________');

disp('For X Uk = ');
disp(Uxk);


test_t1_P = norm ( (I-(Upk*Upk'))*z1);
test_t1_X= norm ( (I-(Uxk*Uxk'))*z1);

test_t2_P  = norm ( (I-(Upk*Upk'))*z2);
test_t2_X = norm ( (I-(Uxk*Uxk'))*z2);
disp('____________________________________________________________________________');
fprintf('Based on the norms t1 should be classified as P becuase \n(Test t1 and P) < (Test t1 and X)\n %u < %u.\n',test_t1_P,test_t1_X);
disp('____________________________________________________________________________');
fprintf('Based on the norms t2 should be classified as X becuase \n(Test t2 and P) > (Test t2 and X)\n %u > %u.\n',test_t2_P,test_t2_X);