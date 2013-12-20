s = []
for i in 1..4 do
s << gets
end

def fl(s)
l = s.length
if s[0].match(/[A-Z]/) and s[l-1].match(/[A-Z]/)
	puts 'true'
true
else
false
end
end

def nos(s)
	if s.split.length == 1
		puts 'true'
		true
	else
		false
	end
end

def twod(s)
	if s.match(/\d{2,}/)
		puts 'true'
		true
	else
		false
	end
end

s.each{ |i|
	puts i
if fl(i) and nos(i) and twod(i)
 puts 'OK'
else
 puts 'ERROR'
end
}


