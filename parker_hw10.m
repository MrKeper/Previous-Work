data = [ 2, 2; 
         4, 2; 
         3, 3; 
         5, 3; 
         6, 3; 
         4, 4; 
         5, 4; 
         7, 4; 
         5, 5; 
         6, 5; 
         7, 6];  
data_size = size(data);
data_row_size = data_size(1);
x_sum = 0; 
y_sum = 0;
for i = 1:data_row_size
    x_sum = x_sum + data(i);
end
for i = data_row_size+1:2*data_row_size
    y_sum = y_sum + data(i);
end

x_average = x_sum/data_row_size;
y_average = y_sum/data_row_size;

S = data;

for i = 1:data_row_size
    S(i) = S(i) - x_average;
end
for i = data_row_size+1:2*data_row_size
    S(i) = S(i) - y_average;
end


C = S'*S;
[V,E] = eig(C);
E_abs = abs(E);
E_sort = sort(E_abs, 1, 'descend');

if(E(1) > E(4))
    principal_value = E(1);
    principal_component = V(1:2);

else
    principal_value = E(4);
    principal_component =  V(3:4);

end


hold on;
for i = 1:data_row_size
        plot(S(i),S(i+data_row_size),'b.');
end

u = [4*principal_component(1),-4*principal_component(1)]
v = [4*principal_component(2),-4*principal_component(2)]
plot(u,v,'r-');

disp('      x          y');
disp('______________________');
disp(S);