for i in 200..1000 do
if(i%11 == 0)
 puts i
end
end

s = 'A123D'
l = s.length
puts s[0].match(/A-Z/)
puts s[l-1].match(/A-Z/)
