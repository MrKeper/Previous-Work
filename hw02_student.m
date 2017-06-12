%Keenan Parker
%1001024878
%Homework #2 Question #4
function decoded_message = hw02_student(message)

pulse0 = ones(1, 10);
pulse0 = pulse0/norm(pulse0);
pulse1 = [ones(1, 5) -ones(1, 5)];
pulse1 = pulse1/norm(pulse1);

message_size = size(message);
message_size = message_size(2);

binary_bit = '';
binary_string = '';

index = 1;

while index < message_size
    ten_elements = message(index:index+9);
    pulse_zero_product = ten_elements*pulse0';
    pulse_one_product = ten_elements*pulse1';
    if (pulse_zero_product >= pulse_one_product)
        binary_bit = '0';
    else
        binary_bit = '1';
    end
    binary_string = strcat(binary_string,binary_bit);
    index = index + 10;
end

first_bit = 1;
decoded_message =  '';

while first_bit < length(binary_string)
    eighth_bit = first_bit+7;
    byte_to_convert = binary_string(first_bit:eighth_bit);
    character_to_add = char(bin2dec(byte_to_convert));
    decoded_message = strcat(decoded_message,character_to_add);
    first_bit = first_bit+8;
end

end
