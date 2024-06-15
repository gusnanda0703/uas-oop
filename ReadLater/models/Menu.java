package models;

import java.util.Optional;
import java.util.Scanner;

public abstract class Menu<T> {
  public Scanner input = new Scanner(System.in);

  public abstract void tampilMenu();

  public abstract void tambah();

  public abstract Optional<T> pilih();

  public abstract Optional<T> pilih(boolean required);

  public abstract void tampilData();

  public abstract void edit();

  public abstract void hapus();

}
