import sys


def compress_slashes(path: str) -> str:
    paths = path.split('/')
    count = 0

    for p in paths:
        if p == '':
            count += 1

    if count > 0:
        i = 0
        while i < count:
            paths.remove('')
            i += 1

    return '/'.join(paths)


def add_dir_on(a: str, b: str) -> str:
    a = compress_slashes(a)
    b = compress_slashes(b)

    if a == '' and b == '':
        return '/'

    a_dirs = a.split('/')
    b_dirs = b.split('/')

    final_path = []

    for d in a_dirs:
        final_path.append(d)

    for d in b_dirs:
        if d == '..' and len(final_path) != 0:
            final_path.pop()

        elif d == '..':
            continue

        elif d == '.':
            continue

        elif not d.isalnum():
            return d + ": No such file or directory"

        else:
            final_path.append(d)

    count = 0

    for p in final_path:
        if p == '':
            count += 1

    if count > 0:
        i = 0
        while i < count:
            final_path.remove('')
            i += 1

    return '/' + '/'.join(final_path)


assert add_dir_on('/', 'abc') == '/abc', '/ abc broken'
assert add_dir_on('/abc/def', 'ghi') == '/abc/def/ghi', '/abc/def ghi broken'
assert add_dir_on('/abc/def', '..') == '/abc', '/abc/def .. broken'
assert add_dir_on('/abc/def', '../..') == '/', '/abc/def ../.. broken'
assert add_dir_on('/abc/def', '../../..') == '/', '/abc/def ../../.. broken'
assert add_dir_on('/abc/def', '.') == '/abc/def', '/abc/def . broken'
assert add_dir_on('/abc/def', '..klm') == '..klm: No such file or directory', '/abc/def ..klm broken'
assert add_dir_on('/abc/def', '......') == '......: No such file or directory', '/abc/def ...... broken'
assert add_dir_on('/abc/def', '../gh///../klm/.') == '/abc/klm', '/abc/def ../gh///../klm/. broken'


def move_to(b: str) -> str:
    return add_dir_on('/', b)


assert move_to('/abc') == '/abc', 'abc/def /abc broken'
assert move_to('/abc/klm') == '/abc/klm', 'abc/def /abc/klm broken'
assert move_to('//////') == '/', '/abc/def ////// broken'

if __name__ == '__main__':

    if len(sys.argv) != 3:
        print(sys.argv[0] + " requires two strings")
        exit(1)

    if sys.argv[2][0] != '/':
        print(add_dir_on(sys.argv[1], sys.argv[2]))

    else:
        print(move_to(sys.argv[2]))
