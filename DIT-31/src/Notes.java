/*
   https://mvnrepository.com/
  
   in downloading dependency in pom.xml
   1. groupID, version
   2. (if not included, then the most compatible version will be instaled),
   3. scope.

   spring is not = spring boot (faster than spring)
   
============================================================================================================

   Group 2
   note: 09-19-2026
  
   ! in production you should not always LOGS exceptions so it get faster and not LAGS.
  
   try catch exception: is not always implemented, especially in Finance
  
   ano nilalagay sa 'try catch' regardless kung mag-error or hindi,
   (block of code) before it stops = 'finally'
  
   through
  
   checked exception:
     + it happens during compilation
   unchecked exception:
     + logical error or wrong API (are not checked at compile-time)
         i.e. 0%10
  
   Demo:
   1. try - exceptions possible
        + bakit kailangan ng Multiple Catch:
            + para i-customize 'yung error messages,
   2. catch
   3. finally -
        + final -
        + finalize -
   4. throws:
        + kapag gumamit tayo ng throw (it can have an error ())
        + pwede 'di ka na mag-try catch, but you can still
   5. throw
   6. multi-catch blocks
  
   best practioces in exception handling:
   1. avoid empty catch blocks and swallowing exceptions
   2. use finally blocks
   3. choose between checked and unchecked exceptions based on the situation

============================================================================================================

   Group 3
   09-26-2026
   Java Generics
  
   purpose to catch type errors at compile time instead of runtime,
  
   Type Parameters.       Placeholder of a data type.
   Generic Classes.
   Generic methods.       automatically determines T based on the value you pass.
   Bounded type parameters. rule about what is allowed in a generic.
   Wildcards (?).         use referring to a generic type but dont know or care what the exact type is.
        + <? extends (object)> = bounded (restricted) - retrieval/reading kasi di alam data type. pababa - minus.
        + <? super (object)>   = outbounded. we know what the specific type. pataas - add.
  
                    Object
                      ↑
                    Number
                      ↑
     ┌───────┬────────┬─────────┬───────┬────────┬─────────┐
    Byte    Short   Integer     Long    Float    Double
  
   extends = KUHA / BASA / Pababa
   extends → flexible sa kung saan ka kukuha ng data.
  
   public static void display(List<? extends Number> list) {
       for (Number n : list) {
           System.out.println(n);
       }
   }
  
   super = LAGAY / ADD / Pataas
   super → flexible sa kung saan ka maglalagay ng data.
  
   public static void addValues(List<? super Integer> list) {
       list.add(10);
       list.add(20);
   }
   
============================================================================================================

	Group 4
	10-10-26
	JAVA COLLECTION FRAMEWORKS 
	
	core interfaces: list, set, queue, map

	arraylist = retrieving/showing data
	linkedlist = manipulate data, insert
	hashmap = labeling of item, for faster lookup
	
	hashset (child of set) = keep track of distinct items
		wrapper classes' parent are objects, so for dynamic use "Object",
		
	iterators and traversals:
	interator methods: 
		hasNext();
		next();
		remove();
	
	Traversal types:
		enhanced for-loop
		listIterator
		fail-fast

*/

