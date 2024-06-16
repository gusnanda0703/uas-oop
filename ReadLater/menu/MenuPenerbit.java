package menu;

import java.util.Optional;

import models.Menu;
import models.Penerbit;
import repositories.UserRepository;
import utils.ScreenHelper;

public class MenuPenerbit extends Menu<Penerbit> {
    UserRepository userRepository;

    public MenuPenerbit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|                DATA Penerbit                |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil Penerbit                         |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah Penerbit                         |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Edit Penerbit                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus Penerbit                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 0 | Kembali                                 |");
            System.out.println("+=============================================+");

            System.out.print("\nSilakan masukan pilihan anda (0...4) : ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> tampilData();
                case 2 -> tambah();
                case 3 -> edit();
                case 4 -> hapus();
                case 0 -> {
                    System.out.println("+=============================================+");
                    System.out.println("|            KEMBALI KE MENU UTAMA            |");
                    System.out.println("+=============================================+\n");
                }
                default -> {
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
                }
            }
        } while (pilihan != 0);
    }

    public void tambah() {
        ScreenHelper.clearConsole();

        System.out.println("+=============================================+");
        System.out.println("|              TAMBAH DATA Penerbit           |");
        System.out.println("+=============================================+");

        System.out.print("Nama Penerbit : ");
        var name = input.nextLine();
        System.out.print("Email         : ");
        var email = input.nextLine();
        System.out.print("Password      : ");
        var password = input.nextLine();

        userRepository.add(new Penerbit(name, email, password));
        System.out.println("Penerbit berhasil ditambahkan.");
        input.nextLine();
    }

    @Override
    public void tampilData() {
        var listPenerbit = userRepository.getPenerbits();
        if (listPenerbit.size() > 0) {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|              TAMPIL DATA Penerbit           |");
            System.out.println("+=============================================+");

            for (var penerbit : listPenerbit) {
                System.out.println("Nama Penerbit : " + penerbit.getName());
                System.out.println("Email         : " + penerbit.getEmail());
                System.out.println("+=============================================+");
            }
        } else {
            System.out.println("Penerbit kosong.");
        }
        input.nextLine();
    }

    @Override
    public Optional<Penerbit> pilih() {
        return pilih(true);
    }

    @Override
    public Optional<Penerbit> pilih(boolean required) {
        var listPenerbit = userRepository.getPenerbits();
        Optional<Penerbit> penerbitDipilih = Optional.empty();
        if (listPenerbit.size() > 0) {
            do {
                System.out.printf("\nPenerbit yang terdafar %d: \n", listPenerbit.size());
                for (var penerbit : listPenerbit) {
                    System.out.printf("%d) %s\n", penerbit.getId(), penerbit.getName());
                }
                System.out.print("Silakan pilih id penerbit : ");
                var penerbitId = input.nextInt();
                penerbitDipilih = listPenerbit.stream().filter(p -> p.getId() == penerbitId).findFirst();
            } while (penerbitDipilih.isEmpty() && required);
        } else {
            System.out.println("Penerbit kosong.");
        }
        input.nextLine();
        return penerbitDipilih;
    }

    public void edit() {
        var penerbitDipilih = pilih();
        if (penerbitDipilih.isPresent()) {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|              EDIT DATA Penerbit             |");
            System.out.println("+=============================================+");

            System.out.println("Biarkan kosong jika tidak ingin mengubah data.");
            var penerbit = penerbitDipilih.get();
            System.out.printf("Nama Penerbit (%s): ", penerbit.getName());
            var name = input.nextLine();
            System.out.printf("Email         (%s): ", penerbit.getEmail());
            var email = input.nextLine();

            penerbit.setName(name.isBlank() ? penerbit.getName() : name);
            penerbit.setEmail(email.isBlank() ? penerbit.getEmail() : email);

            System.out.println("Penerbit berhasil diubah.");
            input.nextLine();
        }
    }

    public void hapus() {
        var penerbitDipilih = pilih();
        if (penerbitDipilih.isPresent()) {
            var penerbit = penerbitDipilih.get();
            userRepository.remove(penerbit);
            System.out.println("Penerbit berhasil dihapus.");
        } else {
            System.out.println("Penerbit tidak ditemukan.");
        }
        input.nextLine();
    }
}
