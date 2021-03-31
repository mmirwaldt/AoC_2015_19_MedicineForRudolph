This is a repository where I solve the puzzle of advent of code, year 2015, day 19:    
Medicine for Rudolph    
See https://adventofcode.com/2015/day/19 for the puzzle    

Some remarks:    
* The approach of generating from "e" to the favourite molecule didn't work.    
I spent all of week, hour after hour for this approach. It doesn't just work. I gave up finally.    
I noticed that applying the replacements in the reverse way works better.    
What do I mean with in "the reverse way"?    
E.g. I can apply replace CaSi in ...PMgArCaSiRnTiMg... by applying "Si <= CaSi" which is reversed "Si => CaSi"    
* If you keep the order of the replacements as they occur in the input,    
then just applying them one by one and one after another doesn't lead you to "e" finally.    
I discovered by accident that just changing the order fixes that issue.    
Therefore I created ShufflingReverseMoleculeReplacement which shuffles the order and tries it out.    
That worked very well because there a many orders which lead you to "e" (and to the right count of replacements).    
However, I hadn't been content with that because I wanted to find out    
which and how many replacements must change their positions in the input.    
I took samples of 100,000 shuffled orders of the replacements and    
discovered that only 3 replacements must change their position:    
Th => ThCa    
Al => ThRnFAr    
Ca => SiTh    
Those 3 replacements must be moved to the head of the list of replacements in this(!) order.    
All other replacements don't need to change their order.    
I wrote the correct order into right_order.txt.    
* I haven't found any pattern for orders which don't work.    
* I haven't neither found any replacement which must always be at the same position in all working orders    
or must never be at the same position in all non-working orders.    
* You need at least 4 rounds so that the order works.    
You iterate over all replacements in every round once and apply each replacement as often as possible.    
