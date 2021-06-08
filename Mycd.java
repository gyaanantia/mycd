public class Mycd {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please input two strings");
            System.out.println(args.length);
            return;
        }

        if (args[1].charAt(0) != '/') {
            System.out.println(addDirOn(args[0], args[1]));
        }

        else {
            System.out.println(addDirOn("/", args[1]));
        }
    }

    private static String addDirOn(String arg1, String arg2) {
        arg1 = compressSlashes(arg1);
        arg2 = compressSlashes(arg2);

        if (arg1.equals("") && arg2.equals("")) {
            return "/";
        }

        String [] arg1_dirs = arg1.split("/");
        String [] arg2_dirs = arg2.split("/");

        String [] final_path = new String[arg1_dirs.length + arg2_dirs.length];

        int path_index = 0;
        for (String d : arg1_dirs) {
            final_path[path_index] = d;
            path_index++;
        }

        for (String d : arg2_dirs) {
            if (d.equals("..") && path_index > 0) {
                path_index--;
                final_path[path_index] = null;
                continue;
            }

            if (d.equals("..") && path_index == 0) {
                final_path[path_index] = null;
                continue;
            }

            else if (d.equals("..")) {
                continue;
            }

            else if (d.equals(".")) {
                continue;
            }

            for (int i = 0; i < d.length(); i++) {
                if (!Character.isLetterOrDigit(d.charAt(i))) {
                    return d + ": No such file or directory";
                }
            }

            final_path[path_index] = d;
            path_index++;
        }

        int count = 0;

        for (String p : final_path) {
            if (p != null && p.equals("")) {
                count++;
            }
        }

        String[] done = new String[final_path.length - count];

        int j = 0;
        for (String p : final_path) {
            if (p != null && !p.equals("")) {
                done[j] = p;
                j++;
            }
        }

        int nulls = 0;
        for (String p : done) {
            if (p == null) {
                nulls++;
            }
        }

        String[] final_string = new String[done.length - nulls];

        int k = 0;
        for (String p : done) {
            if (p != null) {
                final_string[k] = p;
                k++;
            }
        }

        return "/" + String.join("/", final_string);
    }

    private static String compressSlashes(String arg) {
        String [] paths = arg.split("/");
        int count = 0;

        for (String path : paths) {
            if (path.equals("")){
                count++;
            }
        }

        String [] shrunken_paths = new String[paths.length - count];

        int j = 0;
        for (String path : paths) {
            if (!path.equals("")) {
                shrunken_paths[j] = path;
                j++;
            }
        }

        return String.join("/", shrunken_paths);
    }
}
