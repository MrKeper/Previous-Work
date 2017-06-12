function musicID_student(filename,songname)
    %Keenan Parker 1001024878
    [x,fs] = audioread(filename);
    [S, F, T] = spectrogram(x(1:100000), 1024, 512, 1024);
    
    spectrogram_title = sprintf('Spectrogram for "%s"',songname);
    figure('name',spectrogram_title);
    spectrogram(x(1:100000), 1024, 512, 1024,'yaxis');
    title(strcat({'Spectrogram for '},songname));
    
    inverted_S = flipud(S);
    [M,I] = max(inverted_S,[],1);
    t  = 1:length(T);
    
    plot_title = sprintf('Plot for "%s"',songname);
    figure('name',plot_title);
    
    subplot(1,2,1)
    scatter(t,I);
    title('constellation map');
    
    subplot(1,2,2)
    scatter(t,abs(M));
    title('magnitudes');
    
    suptitle(songname);
end