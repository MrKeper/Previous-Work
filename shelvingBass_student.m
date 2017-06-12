function y = shelvingBass_student(x, fs, g, fc)
    %Keenan Parker
mu = 10^(g/20);
theta = (2*pi*fc)/fs;
gamma = (1-(4/(1+mu)*tan(theta/2)))/(1+(4/(1+mu)*tan(theta/2)));
alpha = (1-gamma)/2;

u(1) = alpha*(x(1) + 0) + gamma*0;
for n = 2:length(x)
    u(n) = alpha*(x(n) + x(n-1)) + gamma*u(n-1);
end

for n = 1:length(x)
    y(n) = x(n) + (mu - 1)*u(n);
end


original = fft(x,fs);
abs_original = abs(original);
half_length = length(abs_original)/2;
N = length(abs_original);
frequencies = (0:N-1)*(fs/N);
figure('name','The Original fft Signal','numbertitle','off');
plot(frequencies(1:half_length),abs_original(1:half_length));
title('The Original fft Signal');


filtered = fft(y,fs);
abs_filtered = abs(filtered);
half_length = length(abs_filtered)/2;
N = length(abs_filtered);
frequencies = (0:N-1)*(fs/N);
figure('name','The Filtered fft Signal','numbertitle','off');
plot(frequencies(1:half_length),abs_filtered(1:half_length));
title('The Filtered fft Signal');



end