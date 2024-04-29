package assn07;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password321";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        Account account = new Account(key,value);
        int ind = Math.abs(key.hashCode())%50;
        if(_passwords[ind]==null){
            _passwords[ind]=account;
        } else {
            Account endacct=_passwords[ind];
            if(endacct.getWebsite().equals(key)){
                endacct.setPassword(value);
                return;
            }
            while(endacct.getNext()!=null) {
                endacct = endacct.getNext();
                if (endacct.getWebsite().equals(key)) {
                    endacct.setPassword(value);
                    return;
                }
            }
            endacct.setNext(account);
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int ind = Math.abs(key.hashCode()) % 50;
        if(_passwords[ind] == null){
            return null;
        }
        else if(_passwords[ind].getWebsite().equals(key)){
            return (V)_passwords[ind].getPassword();
        }
        else {
            assn07.Account nextind = _passwords[ind].getNext();
            while(nextind != null){
                if(nextind.getWebsite().equals(key)){
                    return (V)nextind.getPassword();
                }
                else {
                    nextind=nextind.getNext();
                }
            }
            return null;
        }
    }

    // TODO: size
    @Override
    public int size() {
        int sizes = 0;
        for (int i = 0; i < _passwords.length; i++){
            if(_passwords[i] != null){
                sizes++;
                Account acct = _passwords[i].getNext();
                while(acct != null){
                    sizes++;
                    acct = acct.getNext();
                }
            }
        }
        return sizes;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> newSet = new HashSet<K>();
        for(int i = 0; i < _passwords.length; i++){
            if(_passwords[i] != null){
                newSet.add((K) _passwords[i].getWebsite());
                Account acct = _passwords[i].getNext();
                while(acct != null){
                    newSet.add((K) acct.getWebsite());
                    acct = acct.getNext();
                }
            }
        }
        return newSet;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode()) % 50;
        V password = null;
        if(_passwords[index] == null){
            return null;
        }
        else if(_passwords[index].getWebsite().equals(key)){
            password = (V) _passwords[index].getPassword();
            Account temp = _passwords[index];
            _passwords[index] = _passwords[index].getNext();
            temp.setNext(null);
            return password;
        }
        else {
            Account acct = _passwords[index];
            while(acct.getNext() != null){
                if(acct.getNext().getWebsite().equals(key)){
                    password = (V) acct.getNext().getPassword();

                    acct.setNext(acct.getNext().getNext());
                    return password;
                }
                else {
                    acct = acct.getNext();
                }
            }
            return null;
        }
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> newlist = new ArrayList<K>();
        for (int i = 0; i < _passwords.length; i++){
            if(_passwords[i] != null){
                Account acct = _passwords[i];
                while(acct != null){
                    if(acct.getPassword().equals(value)){
                        newlist.add((K) acct.getWebsite());
                    }
                    acct = acct.getNext();
                }
            }
        }
        return newlist;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return (enteredPassword.compareTo(MASTER_PASSWORD)==0);
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4


        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
