function [A, B] = hw01_student()
    % run hw01_main.m
    
    % Keenan Parker;
    
    %Part 1
    left = zeros(1,10);
    left([6:10]) = 1;
    left = left';
    
    mid([1:10]) = 9;
    mid = mid';
    
    right  = [1:10];
    right = right';
    
    A = [left mid right];
     
    %Part 2
    B = zeros(3,4);
    a = A';
    b = a(19:30);
    j = 12;
    for i = 1:12
        B(i) = b(j);
        j = j-1;
    end
    B = B';
    
    
    
   %Part 3
   t = [0:0.001:0.5];
   plot( t,sin(2*pi*5*t) +  cos(2*pi*10*t));
   axis([0 0.5 -2 1.5]);
   title('sin(10\pit)+ cos(20\pit)');
   
   %Part 4
   t = [0:0.01:0.5];
   figure(2);
   s = sin(2*pi*5*t);
   subplot(3,1,1);
   plot(t,s);
   axis([0 0.5 -1.5 1.5]);
   
   subplot(3,1,2);
   stem(t,s,'r');
   axis([0 0.5 -1.5 1.5]);

   subplot(3,1,3);
   plot(t,sin(2*pi*5*t));
   axis([0 0.5 -1.5 1.5]);
   hold on;
   stem(t,sin(2*pi*5*t),'r');
   hold off;
   
   
end
