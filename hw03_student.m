function [y1,y2] = hw03_student()
    x1 = [1:1000];
    x2 = [1:1000];
    Ts = 1/1000;
    
    for i = 1:1000
        x1(i) = 3*cos(10*pi*i*Ts);
        x2(i)= 7*cos(200*pi*i*Ts);
    end
    
    x = x1 + x2;
    
    h1 = [1/5,1/5,1/5,1/5,1/5];
    h2 = [1/10,1/10,1/10,1/10,1/10,1/10,1/10,1/10,1/10,1/10];
    y1 = conv(x,h1);
    y2 = conv(x,h2);
    
    subplot(5,1,1);
    plot(x1);
    title('x1');
    axis([0 1000 -15 15]);
    subplot(5,1,2);
    plot(x2);
    title('x2');
    axis([0 1000 -15 15]);
    subplot(5,1,3);
    plot(x);
    title('x');
    axis([0 1000 -15 15]);
    subplot(5,1,4);
    plot(y1);
    title('y1');
    axis([0 1000 -15 15]);
    subplot(5,1,5);
    plot(y2);
    title('y2');
    axis([0 1000 -15 15]);
end