package dsls.regex

object Program extends App {
  import Literal._
  import Union._
  import Concat._
  import Star._
    
  // Characters that support regular expressions.
  
  val zero = '0'
  val one = '1'
  val two = '2'
  val three = '3'
  val four = '4'
  val five = '5'
  val six = '6'
  val seven = '7'
  val eight = '8'
  val nine = '9'
  
  require(zero matches "0")
  require(one matches "1")
  require(two matches "2")
  require(three matches "3")
  require(four matches "4")
  require(five matches "5")
  require(six matches "6")
  require(seven matches "7")
  require(eight matches "8")
  require(nine matches "9")
  
  //Strings that support regular expressions
  val answer = "42"

  require(answer matches "42")
              
  // Union operator for regular expressions.
  val digit = '0' || '1' || '2' || '3' || '4' || '5' || '6' || '7' || '8' || '9' 

  require(digit matches "0")
  require(digit matches "1")
  require(digit matches "2")
  require(digit matches "3")
  require(digit matches "4")
  require(digit matches "5")
  require(digit matches "6")
  require(digit matches "7")
  require(digit matches "8")
  require(digit matches "9")      

  // Concatenation operator for regular expressions.
  val pi = '3' ~ '1' ~ '4'

  require(pi matches "314")
  
  val zeroOrMoreDigits = digit <*>
  
  require(zeroOrMoreDigits matches "")
  require(zeroOrMoreDigits matches "0")
  require(zeroOrMoreDigits matches "9")
  require(zeroOrMoreDigits matches "09")
  require(zeroOrMoreDigits matches "987651234")
  
  // Plus operator for regular expressions.
  val number = digit <+> 
  
  require(!(number matches ""))
  require(number matches "0")
  require(number matches "9")
  require(number matches "09")
  require(number matches "987651234")

  // Repetition operator for regular expressions.
//  val cThree = 'c'{3}
  
  val cThree = Concat(Literal('c'), Concat(Literal('c'), Literal('c')))
  
  require(cThree matches "ccc")
  
//  val pattern = "42" || ( ('a' <*>) ~ ('b' <+>) ~ ('c'{3}))

  val aStar = Star(Literal('a'))
  val bPlus = Concat(Literal('b'), Star(Literal('b')))
  val pattern = Union(answer, Concat(aStar, Concat(bPlus, cThree)))
  
  require(pattern matches "42")
  require(pattern matches "bccc")
  require(pattern matches "abccc")
  require(pattern matches "aabccc")
  require(pattern matches "aabbccc")
  require(pattern matches "aabbbbccc")
 
  // Additional patterns that can be represented by the regular expressions.
  val helloworld = ("hello" <*>) ~ "world"

  
  require(helloworld matches "helloworld")
  require(helloworld matches "world")
  require(helloworld matches "hellohelloworld")
  
//  val telNumber = '(' ~ digit{3} ~ ')' ~ digit{3} ~ '-' ~ digit{4}
  
  val threeDigits = Concat(digit, Concat(digit, digit))
  val fourDigits = Concat(threeDigits, digit)
  val areaCode = Concat(Literal('('), Concat(threeDigits, Literal(')')))
  val telNumber = Concat(areaCode, Concat(threeDigits, Concat(Literal('-'), fourDigits)))

  require(telNumber matches "(202)456-1111")
}
