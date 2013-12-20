a = gets
b = gets

c = a.to_i + b.to_i

puts c
d = []
for i in 1..3 do
d << gets.to_i
end
puts d
puts 'Max - '
puts d.max

if d.max in 1..100
puts 'Max in 1 to 100'
end
