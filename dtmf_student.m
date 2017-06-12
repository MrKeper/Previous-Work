function  [phoneNumber] = dtmf_student(signal, fs)
%Keenan Parker 1001024878
L = 64;

freq_col = [697 770 852 941];
freq_row =  [1209 1336 1447];
phoneNumber = '';

number_length = length(signal)/4000;
start_position = 1;
end_position = 4000;
N = 0:L;

for n = 1:number_length
    max_mean = 0;
    col_freq =  0;
    row_freq =  0;
    tone = signal(start_position:end_position);
    
    for i = 1:4
        h = (2/L) * cos( (2*pi*freq_col(i)*N)/fs);
        y = conv(h,tone);
        z = mean(y.^2);
        if(z > max_mean)
            max_mean = z;
            col_freq =  freq_col(i);
        end
    end
    
    max_mean = 0;
    for i = 1:3
        h = (2/L) * cos( (2*pi*freq_row(i)*N)/fs);
        y = conv(h,tone);
        z = mean(y.^2);
        if(max_mean < z)
            max_mean = z;
            row_freq =  freq_row(i);
        end
    end
    
    if(col_freq ==  697 && row_freq == 1209)
        phoneNumber = strcat(phoneNumber,'1');
    end
     if(col_freq ==  697 && row_freq == 1336)
        phoneNumber = strcat(phoneNumber,'2');
     end
     if(col_freq ==  697 && row_freq ==  1447)
        phoneNumber = strcat(phoneNumber,'3');
     end
     
     if(col_freq == 770 && row_freq ==  1209)
        phoneNumber = strcat(phoneNumber,'4');
    end
     if(col_freq == 770 && row_freq ==  1336)
        phoneNumber = strcat(phoneNumber,'5');
     end
     if(col_freq == 770 && row_freq ==  1447)
        phoneNumber = strcat(phoneNumber,'6');
     end
     
     if(col_freq == 852 && row_freq ==  1209)
        phoneNumber = strcat(phoneNumber,'7');
    end
     if(col_freq == 852 && row_freq ==  1336)
        phoneNumber = strcat(phoneNumber,'8');
     end
     if(col_freq == 852 && row_freq ==  1447)
        phoneNumber = strcat(phoneNumber,'9');
     end
     
     if(col_freq == 941 && row_freq ==  1209)
        phoneNumber = strcat(phoneNumber,'*');
    end
     if(col_freq == 941 && row_freq ==  1336)
        phoneNumber = strcat(phoneNumber,'0');
     end
     if(col_freq == 941 && row_freq ==  1447)
        phoneNumber = strcat(phoneNumber,'#');
     end

    start_position = end_position+1;
    end_position = end_position + 4000;
end

end
