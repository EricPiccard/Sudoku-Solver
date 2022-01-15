import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;


//char c = reader.next().charAt(0);

//reader.useDelimiter("");

public class Sudoku
{
  public static void main(String[]arg) throws FileNotFoundException
  {
    char[][] list = new char[16][16];
    Scanner filein = new Scanner(new File("in.txt"));
    int j = 0;
    String holder;
    
      while(filein.hasNextLine() && j < 16) 
      {
        holder = filein.nextLine();
        
        for(int i = 0; i < 16; i++)
        {
          list[j][i] = holder.charAt(i);
        }
        j++;
      }

      filein.close();
      intial(list);

      
      
  }
  
  public static void intial(char [][] list) 
  {
    System.out.println("csp");
    System.out.println((-1) * csp(list) + " val:");
    System.out.println("mrv");
    System.out.println((-1) * mrv(list) + " val:");
  }
  public static int csp(char[][] list)
  {
    char h = '-';
    int count = 0;;
    //can probably clean up 
    //todo: shorten code so it doesnt loop each time probably could pass on intial value to start at.....
    for(int i = 0; i < 16; i++)
    {
      for(int j = 0; j < 16; j++)
      {
        if(list[i][j] == h)
        {
          count = update(list, i, j);
          //System.out.println(count);
        }
        if(count != 0) //received value for update dont loop return select value phase
        {
          //System.out.println(count);
          return count;
        }
      }
      
    }
    for(int i = 0; i < 16; i++)
    {
      for(int j = 0; j < 16; j++)
      {
        System.out.print(list[i][j]);
      }
      System.out.println();
    }
    return (-1);
    //finished all items in list 
  }
  
    public static int mrv (char[][] list)
  {
    char h = '-';
    boolean found = false;
    int row = 0;
    int col = 0;
    int length = 20;
    
    for(int i = 0; i < 16; i++)
    {
      for(int j = 0; j < 16; j++)
      {
        if(list[i][j] == h)
        {
          char[] unsl = unselected(list, j, i);
          if(unsl.length < length)
          {
            row = i;
            col = j;
            found = true;
            length = unsl.length;
          }
        }
      }
      
    }
    //System.out.println(length);
    if(found == false)
    {
      for(int i = 0; i < 16; i++)
      {
        for(int j = 0; j < 16; j++)
        {
          System.out.print(list[i][j]);
        }
        System.out.println();
    }
      return (-1);
    }
    int count = update1(list, row, col);
    return count;
  }
    public static int update1(char[][] list, int i, int j)
  {
    char[] unsl = unselected(list, j, i);
    //System.out.println();
    
    int count = 0;
    int neg;
    
    if(unsl.length == 0) //problem return to the past
    {
      return 1;
    }
    
    char[] holder = new char[unsl.length];
    char[][] listH = new char[16][16];
    
    for(int k = 0; k < 16; k++)
      {
        for(int l = 0; l < 16; l++)
        {
          listH[k][l] = list[k][l];
        }
      }
    for(int k = 0; k < unsl.length; k++)
    {
      holder[k] = unsl[k];
    }
    
    
    
    for(int k = 0; k < holder.length; k++)
    {
      listH[i][j] = holder[k];

      neg = mrv(listH);
      count ++;
      if(neg < 0) 
      {

        return (((-1) * count) + neg);
      }
      else if(count == 1)
      { 
        count = count;
      }
      else
      {
        count = count + neg;
      }
    }
    //selection failed
    return count;
  }
    
  
  
  
  public static int update(char[][] list, int i, int j)
  {
    char[] unsl = unselected(list, j, i);

    
    int count = 0;
    int neg;

    if(unsl.length == 0) //problem return to the past
    {
      return 1;
    }
    
    char[] holder = new char[unsl.length];
    char[][] listH = new char[16][16];
    
    for(int k = 0; k < 16; k++)
      {
        for(int l = 0; l < 16; l++)
        {
          listH[k][l] = list[k][l];
        }
      }
    for(int k = 0; k < unsl.length; k++)
    {
      holder[k] = unsl[k];

    }
    
    
    
    for(int k = 0; k < holder.length; k++)
    {
      listH[i][j] = holder[k];

      neg = csp(listH);
      count++;
      if(neg < 0) //return negative, program is finished
      {
       // System.out.println((((-1) * count) - neg));
        return (((-1) * count) + neg);
      }
      else if(neg == 1)
      { 
        count = count;
      }
      else
      {
        count = count + neg;
      }
    }
    //selection failed
    return count;
  }
  
  

  
  
  public static char[] unselected(char [][] list, int x, int y)
  {
    char[] unsl = new char[16];
    char[] row = row(list, y);
    char[] col = column(list, x);
    char[] sqr = box(list, x, y);
    
              if(y == 1 && x == 14)
          {
                for(int j = 0; j < sqr.length; j++)
                   {
                  //System.out.print(sqr[j]);
                }
            //System.out.println(sqr[j]);
          }
    
    
    
    char[] var1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    boolean test = false;
    int loc = 0;
    for(int i = 0;  i < 16; i++)
    {
      for(int j = 0; j < row.length; j++)
      {
        if(var1[i] == row[j])
        {
          test = true;
        }
      }
      if(test != true)
      {
        for(int j = 0; j < col.length; j++)
        {
          if(var1[i] == col[j])
        {
          test = true;
        }
        }
      if(test != true)
      {
        for(int j = 0; j < sqr.length; j++)
        {
          if(var1[i] == sqr[j])
        {
          test = true;
          if(y == 1 && x == 14)
          {
            //System.out.println(var1[i]);
            //System.out.println(sqr[j]);
          }
        }
        }
      }
      }
      if(test != true)
      {
        unsl[loc] = var1[i];
        //System.out.print(unsl[loc]);
        loc++;
      }
      test = false;
    }
    
    
    if(loc == 0)
    {
      char[] dump = new char[0];
      return dump;
    }
    char[] fixed = new char[loc];
    for(int k = 0; k < fixed.length; k++)
    {
      fixed[k] = unsl[k];
    }

    return fixed;
  }
  
  public static char[] row(char[][] list, int y)
  {
    char[] row = new char[16];
    char h = '-';
    for(int i = 0; i < 16; i++)
    {
      if(list[y][i] != h)
      {
      row[i] = list[y][i];
      }
    }
    return row;
  }
  public static char[] column(char[][] list, int x)
  {
    char[] col = new char[16];
    char h = '-';
    for(int i = 0; i < 16; i++)
    {
     if(list[i][x] != h)
     {
      col[i] = list[i][x];
     }
    }
    return col;
  }
  public static char[] box(char[][] list, int x, int y)
  {
    char[] sqr = new char[16];
    int row = (int)(y / 4);
    int col = (int)(x / 4);
    char h = '-';
    int loc = 0;
    for(int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        if(list[(row * 4) + i][(col * 4) +j] != h)
        {
        sqr[loc] = list[(row * 4) + i][(col * 4) +j];
        loc++;
        }
        
      }
    }
    

    return sqr;
    
  }
  
}