package SatSolver;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;



public class SAT {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Type your expression");
		String expression=s.nextLine();
		
		String[] tokens=expression.split("");
		Stack print=new Stack();
		Stack operator=new Stack();
		
		
		
		for(String a:tokens)
		{
			if(operator.empty())
			{
				if(a.equals("~")|a.equals("^")|a.equals("v")|a.equals(">")|a.equals("-"))
				{
				operator.push(a);
				continue;
				}
			}
			if(a.equals("("))
			{
				operator.push(a);
				continue;
			}
			if(a.equals("~"))
			{
				operator.push(a);
				continue;
			}
			if(a.equals(">"))
			{
				if(operator.peek().equals("(")|operator.peek().equals("-"))
				{
					operator.push(a);
					continue;
				}
				
				while(operator.peek().equals("^")|operator.peek().equals("v")|operator.peek().equals("~"))
				{
					print.push(operator.pop());
					if(operator.empty())
						break;
				}
				operator.push(a);
				continue;
				
			}
			if(a.equals(")"))
			{
				while(!(operator.peek().equals("(")))
				{
					print.push(operator.pop());
				}
				operator.pop();
				continue;
			}
			if(a.equals("v"))
			{
				if(operator.peek().equals("(")|operator.peek().equals("-")|operator.peek().equals(">"))
				{
					operator.push(a);
					continue;
				}
				while(operator.peek().equals("^")|operator.peek().equals("~"))
				{
					print.push(operator.pop());
					if(operator.empty())
						break;
				}
				operator.push(a);
				continue;
			}
			if(a.equals("^"))
			{
				if(operator.peek().equals("(")|operator.peek().equals("-")|operator.peek().equals(">")|operator.peek().equals("v"))
				{
					operator.push(a);
					continue;
				}
				while(operator.peek().equals("~"))
				{
					print.push(operator.pop());
					if(operator.empty())
						break;
				}
				operator.push(a);
				continue;
			}
			if(a.equals("-"))
			{
				if(operator.peek().equals("("))
				{
					operator.push(a);
					continue;
				}
				while(operator.peek().equals("~")|operator.peek().equals("v")|operator.peek().equals("^")|operator.peek().equals(">"))
				{
					print.push(operator.pop());
					if(operator.empty())
						break;
				}
				operator.push(a);
				continue;
			}
			else
			{
				print.push(a);
			}
		}
		while(!operator.empty())
			print.push(operator.pop());
		
		//System.out.println(Arrays.toString(print.toArray()));
		
		//
		
		
		String[] literals=expression.replace("(","").replace(")","").replaceAll("~","").split("[\\^v>]");
		
		Set<String> uniqueWords = new HashSet<String>(Arrays.asList(literals));
		
		int size=uniqueWords.size();
		HashMap uw=new HashMap();
		
//		for(String alpha:uniqueWords)
//		{
//			
//			uw.put(alpha, 2);
//			
//		}
	
	
		
		
		int[] input=new int[size];
		boolean first,second=false;
		boolean r;
		
		
		 List<String> list = new ArrayList<String>(print);
		 Stack result=new Stack();
		 List<String> resultList = new ArrayList<String>(result);
		 ArrayList resultArray=new ArrayList();
		 ArrayList values=new ArrayList();
		//
		 
		        int rows = (int) Math.pow(2,size);

		        for (int i=0; i<rows; i++) {
		        	Iterator iterator=uniqueWords.iterator();
		            for (int j=size-1; j>=0&iterator.hasNext(); j--) {
		            	
		              uw.put(iterator.next(),i/(int) Math.pow(2, j)%2);  
		              
		              
		            }
		            
		            for(String at:list)
		            {
		            	
		            	if(!((at.equals("^"))|(at.equals("v"))|(at.equals("~"))|(at.equals(">"))|(at.equals("-"))))
		            	{
		            		
		            		result.push(uw.get(at));
		            		continue;
		           		}
		            	if(at.equals(">"))
		            	{
		            		int se= (int) result.pop();
		            		if(se==1)
		            			second=true;
		            		else
		            			second=false;
		            		
		            		int f=(int) result.pop();
		            		if(f==1)
		            			first=true;
		            		else
		            			first=false;
		            	
		            		r=(!first)|second;
		            		int re=r?1:0;
		            		result.push(re);
		            		continue;
		            	}
		            	if(at.equals("^"))
		            	{
		            		int se= (int) result.pop();
		            		if(se==1)
		            			second=true;
		            		else
		            			second=false;
		            		
		            		int f=(int) result.pop();
		            		if(f==1)
		            			first=true;
		            		else
		            			first=false;
		            	
		            		r=first&second;
		            		int re=r?1:0;
		            		result.push(re);
		            		continue;
		            	}
		            	if(at.equals("v"))
		            	{
		            		int se= (int) result.pop();
		            		if(se==1)
		            			second=true;
		            		else
		            			second=false;
		            		
		            		int f=(int) result.pop();
		            		if(f==1)
		            			first=true;
		            		else
		            			first=false;
		            	
		            		r=first|second;
		            		int re=r?1:0;
		            		result.push(re);
		            		continue;
		            	}
		            	if(at.equals("~"))
		            	{
		            		int se= (int) result.pop();
		            		if(se==1)
		            			second=true;
		            		else
		            			second=false;
		            		
		            		r=!second;
		            		int re=r?1:0;
		            		result.push(re);
		            		continue;
		            	}
		            	if(at.equals("-"))
		            	{
		            		int se= (int) result.pop();
		            		if(se==1)
		            			second=true;
		            		else
		            			second=false;
		            		
		            		int f=(int) result.pop();
		            		if(f==1)
		            			first=true;
		            		else
		            			first=false;
		            	
		            		r=((!first)|second)&((!second)|first);
		            		int re=r?1:0;
		            		result.push(re);
		            		continue;
		            	}
		            
		            }
		            resultArray.add(result.pop());
//		        	while(!result.empty())
//		    			result.pop();
		            
		        	
		            
		        }
		        System.out.println("In CNF:");
		  int tt=0;
			  rows = (int) Math.pow(2,size);

		        for (int i=0; i<rows; i++) {
		        	
		        	
		            for (int j=size-1; j>=0; j--)
		            {
		            	
		              values.add(i/(int) Math.pow(2, j)%2);  
		              
		              
		            }
		            
		            
		            if((int)resultArray.get(i)==0)
		            	
		            {	
		            	if(tt==0)
		            	{
		            		System.out.print("(");
		            		tt++;
		            	}
		            	else if(tt>0)
		            		System.out.print(" ^ (");
		            	
		            	Iterator eg=uniqueWords.iterator();
		            	for(int l=0;l<values.size()&eg.hasNext();l++)
		            	{
		            		if((int)values.get(l)==0)
		            			System.out.print(eg.next()+"v");
		            		else
		            			System.out.print("~"+eg.next()+"v");
		            		
		            	}
		            	System.out.print("\b)");
		            	
		            } 
		            
		            while(!values.isEmpty())
	            		values.removeAll(values);
		           
		            
		  }
		             
		  int check=0;
		  for(int i=0;i<resultArray.size();i++)
		  {
			  if((int)resultArray.get(i)==1)
			  {
				  check++;
			  }
		  }
		  System.out.println();
		  if(check==resultArray.size())
			  System.out.println("It is a Tautology");
		  else if(check==0)
			  System.out.println("It is a Contradiction");
		  else
			  System.out.println("It is Satisfiable");
	}
	

}
