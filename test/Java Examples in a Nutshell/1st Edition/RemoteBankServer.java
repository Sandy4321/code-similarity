// This example is from _Java Examples in a Nutshell_. (http://www.oreilly.com)
// Copyright (c) 1997 by David Flanagan
// This example is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.
// For any commercial use, see http://www.davidflanagan.com/javaexamples

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import Bank.*;

/**
 * This class implements the remote methods defined by the RemoteBank
 * interface.  It has a serious shortcoming, though: all account data is
 * lost when the server goes down.
 **/
public class RemoteBankServer extends UnicastRemoteObject implements RemoteBank
{
  /** 
   * This nested class stores data for a single account with the bank 
   **/
  class Account {
    String password;                      // account password
    int balance;                          // account balance
    Vector transactions = new Vector();   // account transaction history
    Account(String password) {
      this.password = password;
      transactions.addElement("Account opened at " + new Date());
    }
  }
  
  /** 
   * This hashtable stores all open accounts and maps from account name
   * to Account object
   **/
  Hashtable accounts = new Hashtable();
  
  /**
   * This constructor doesn't do anything, but because it throws the
   * same exception that the superclass constructor throws, it must be
   * declared.
   **/
  public RemoteBankServer() throws RemoteException { super(); }
  
  /** 
   * Open a bank account with the specified name and password 
   * This method is synchronized to make it thread safe, since it 
   * manipulates the accounts hashtable.
   **/
  public synchronized void openAccount(String name, String password)
       throws RemoteException, BankingException {
    // Check if there is already an account under that name
    if (accounts.get(name) != null) 
      throw new BankingException("Account already exists.");
    // Otherwise, it doesn't exist, so create it.
    Account acct = new Account(password);
    // And register it
    accounts.put(name, acct);
  }

  /**
   * This convenience method is not a remote method.  Given a name and password
   * it checks to see if an account with that name and password exists.  If
   * so, it returns the Account object.  Otherwise, it throws an exception.
   **/
  public Account verify(String name, String password) 
       throws BankingException {
    synchronized(accounts) {
      Account acct = (Account)accounts.get(name);
      if (acct == null) throw new BankingException("No such account");
      if (!password.equals(acct.password)) 
        throw new BankingException("Invalid password");
      return acct;
    }
  }

  /** 
   * Close the named account.  This method is synchronized to make it 
   * thread safe, since it manipulates the accounts hashtable.
   **/
  public synchronized FunnyMoney closeAccount(String name, String password)
       throws RemoteException, BankingException {
    Account acct;
    acct = verify(name, password);
    accounts.remove(name);
    // Before changing the balance or transactions of any account, we first
    // have to obtain a lock on that account to be thread safe.
    synchronized (acct) {
      int balance = acct.balance;
      acct.balance = 0;
      return new FunnyMoney(balance);
    }
  }

  /** Deposit the specified FunnyMoney to the named account */
  public void deposit(String name, String password, FunnyMoney money) 
       throws RemoteException, BankingException {
    Account acct = verify(name, password);
    synchronized(acct) { 
      acct.balance += money.amount; 
      acct.transactions.addElement("Deposited " + money.amount + 
                                   " on " + new Date());
    }
  }

  /** Withdraw the specified amount from the named account */
  public FunnyMoney withdraw(String name, String password, int amount)
       throws RemoteException, BankingException {
    Account acct = verify(name, password);
    synchronized(acct) {
      if (acct.balance < amount) 
        throw new BankingException("Insufficient Funds");
      acct.balance -= amount;
      acct.transactions.addElement("Withdrew " + amount + " on "+new Date());
      return new FunnyMoney(amount);
    }
  }

  /** Return the current balance in the named account */
  public int getBalance(String name, String password)
       throws RemoteException, BankingException {
    Account acct = verify(name, password);
    synchronized(acct) { return acct.balance; }
  }

  /** 
   * Return a Vector of strings containing the transaction history
   * for the named account
   **/
  public Vector getTransactionHistory(String name, String password)
       throws RemoteException, BankingException {
    Account acct = verify(name, password);
    synchronized(acct) { return acct.transactions; }
  }

  /**
   * The main program that runs this RemoteBankServer.
   * Create a RemoteBankServer object and give it a name in the registry.
   * Read a system property to determine the name, but use "FirstRemote"
   * as the default name.  This is all that is necessary to set up the
   * service.  RMI takes care of the rest.
   **/
  public static void main(String[] args) {
    try {
      // Create a bank server object
      RemoteBankServer bank = new RemoteBankServer();
      // Figure out what to name it
      String name = System.getProperty("bankname", "FirstRemote");
      // Name it that
      Naming.rebind(name, bank);
      // Tell the world we're up and running
      System.out.println(name + " is open and ready for customers.");
    }
    catch (Exception e) {
      System.err.println(e);
      System.err.println("Usage: java [-Dbankname=<name>] RemoteBankServer");
      System.exit(1); // Force an exit because there might be other threads
    }
  }
}
