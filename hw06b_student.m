function [aa,bb] = hw06b_student()
%Keenan Parker, 1001024878
    t = (0:0.002:1);
    x = cos(2*pi*2*t)+cos(2*pi*10*t)+cos(2*pi*17*t)+cos(2*pi*33*t);
    w1 = pi/25;
    w2 = (33*2*pi)/500;
    u = 2*cos(w1);
    v = 2*cos(w2);
    n = 0.9372;
    
    bb = [1 -(u+v) 2+(u*v) -(u+v) 1];
    
    aa = [1 -n*(u+v) (n^2)*(2+(u*v)) -(n^3)*(u+v) (n^4)];
    
    y = filter(bb, aa, x);
    target = cos(2*pi*2*t)+cos(2*pi*17*t);
    
       
    subplot(3,1,1)
    plot(t,x)
    axis([0 1 -5 5]);
    title('2Hz,10Hz,17Hz,33Hz plot');
    
    
    subplot(3,1,2)
    plot(t,y)
    axis([0 1 -5 5]);
    title('10Hz,33Hz Combined Notch Filter plot');
    
    
    subplot(3,1,3)
    plot(t,target)
    axis([0 1 -5 5]);
    title('2Hz,17Hz plot');

 
end