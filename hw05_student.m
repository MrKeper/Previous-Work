function hw05_student(data, fs)
%Keenan Parker 1001024878

%lowpass FIR Filter
fc = 50;
L = 21;
M = L -1;
w = [];
ft = fc/fs;
for n = 1:L
    if(n == M/2)
        w(n) = 2*ft;
    else
        w(n) = sin(2*pi*ft*(n-M/2)) / (pi*(n-M/2));
    end
end

figure('name','Lowpass Filter','numbertitle','off')
subplot(3,1,1);
plot(data);
title('original signal');

subplot(3,1,2);
t = (0:0.0005:1);
x = cos(2*pi*4*t);
plot(x);
axis([0 length(data) -1.5 1.5]);
title('4 Hz signal');

subplot(3,1,3);
y = conv(data,w);
plot(y);
axis([0 length(data) -2 2]);
title('application of lowpass filter');


%highpass FIR Filters

fc = 280;
L = 21;
M = L -1;
w = [];
ft = fc/fs;
for n = 1:L
    if(n == M/2)
        w(n) = 1 - 2*ft;
    else
        w(n) = -sin(2*pi*ft*(n-M/2)) / (pi*(n-M/2));
    end
end

figure('name','Highpass Filter','numbertitle','off')
subplot(3,1,1);
plot(data);
axis([0 100 -5 5]);
title('original signal');

subplot(3,1,2);
t = (0:0.0005:1);
x = cos(2*pi*330*t);
plot(x);
axis([0 100 -2 2]);
title('330 Hz signal');

subplot(3,1,3);
y = conv(data,w);
plot(y);
axis([10 110 -2 2]);
title('application of highpass filter');

end
 