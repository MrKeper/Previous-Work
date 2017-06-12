function [L, fc, hNew, y] = removeNoise_student(x, fs)
%Keenan Parker 1001024878
L = 25;
M = L - 1;
w = [];
h = [];
fc = 7000;
ft = fc/fs;

for n = 1:L
    if(n == M/2)
        w(n) = 2*ft;
    else
        w(n) = sin(2*pi*ft*(n-M/2)) / (pi*(n-M/2));
    end
end

for i = 1:L
   h(i) = 0.54 - 0.46*cos((2*pi*i)/(M));
end

hNew = h .* w;

%plot of the fft of the original signal
orginal_graph = fft(x,fs);
abs_og_graph = abs(orginal_graph);
half_length = length(abs_og_graph)/2;
N = length(abs_og_graph);
frequencies = (0:N-1)*(fs/N);

figure('name','The Original fft Signal','numbertitle','off');
plot(frequencies(1:half_length),abs_og_graph(1:half_length));
title('The Original fft Signal');

%Plot of the fft of the processed signal
y = conv(hNew,x);
applied_graph = fft(y,fs);
abs_applied_graph = abs(applied_graph);
half_length = length(abs_applied_graph)/2;
N = length(abs_applied_graph);
frequencies = (0:N-1)*(fs/N);
figure('name','The Processed fft Signal','numbertitle','off');
plot(frequencies(1:half_length),abs_applied_graph(1:half_length));
title('The Processed fft Signal');

%Plot the frequency response of the filter
H = freqz(hNew, 1, 0:fs/2, fs);
figure('name','Frequency Response of the Filter','numbertitle','off');
plot(abs(H));
title('Frequency Response of the Filter');

end