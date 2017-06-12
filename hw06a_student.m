function [aa,bb] = hw06a_student()
%Keenan Parker, 1001024878
    t = (0:0.002:1);
    x = cos(2*pi*10*t)+cos(2*pi*17*t)+cos(2*pi*33*t);
    w = pi/25;
    
    bb = [1 -2*cos(w) 1];
    
    aa = [1 -0.9372*2*cos(w) 0.9372*0.9372];
    
    y = filter(bb, aa, x);
    u = cos(2*pi*17*t)+cos(2*pi*33*t);
    
    subplot(3,1,1)
    plot(t,x)
   axis([0 1 -5 5]);
   title('10Hz,17Hz,33Hz plot');
    
    
    subplot(3,1,2)
    plot(t,y)
    title('10Hz Notch Filter plot');
    
    
    subplot(3,1,3)
    plot(t,u)
    axis([0 1 -5 5]);
    title('17Hz,33Hz plot');

end