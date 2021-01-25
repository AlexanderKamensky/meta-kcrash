SUMMARY = "Kernel dump file access library"
DESCRIPTION = "A library that provides an abstraction layer for reading kernel dump \
core files.  It supports different kernel dump core formats, virtual \
to physical translation, Xen mappings and more."

HOMEPAGE = "https://github.com/ptesarik/libkdumpfile"

LICENSE = "GPLv2 & GPLv3 & LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=4ca7e035a55f25a55e4a1fde7c9d621c \
                    file://COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRCBRANCH ?= "tip"
SRCREV = "7ec08b983f6a27f94653b3ce8ea30c0563ade2d5"

PV = "v0.4.0+git${SRCPV}"

SRC_URI = "git://github.com/ptesarik/libkdumpfile;branch=${SRCBRANCH} \
          file://0001-kdump-gdbserver-adds-kdump-gdbserver-utility.patch \
          "

S = "${WORKDIR}/git"

DEPENDS = "zlib python3"

inherit autotools pkgconfig python3native python3-dir

PACKAGECONFIG ??= ""
PACKAGECONFIG[lzo] = "--with-lzo,--without-lzo,lzo"
PACKAGECONFIG[snappy] = "--with-snappy,--without-snappy,snappy"

# install kdump-gdbserver manuelly since it doesn't have automake logic yet
do_install_append () {
        install -m 755 ${S}/gdbserver/kdump-gdbserver ${D}${bindir}
        install -d ${D}${datadir}/libkdumpfile/gdbserver
        install -m 644 ${S}/gdbserver/README.md ${D}${datadir}/libkdumpfile/gdbserver
        install -m 644 ${S}/gdbserver/KdumpGdbCommands.py ${D}${datadir}/libkdumpfile/gdbserver
        #sed -e s,/usr/bin/env\ ,${bindir}/, -i ${D}${bindir}/kdump-gdbserver
}

# remove rpath from cpython .so since libkdumpfile libraries are at standard location
do_install_append_class-nativesdk () {
        chrpath --delete ${D}${libdir}/${PYTHON_DIR}/site-packages/*.so || true
}

PACKAGES_prepend += "python3-libkdumpfile ${PN}-gdbserver "
FILES_python3-libkdumpfile += "${PYTHON_SITEPACKAGES_DIR}"
FILES_${PN}-gdbserver += "\
                ${bindir}/kdump-gdbserver \
                ${datadir}/libkdumpfile/gdbserver \
"
RDEPENDS_${PN}-gdbserver = "python3-libkdumpfile"

COMPATIBLE_HOST = '(x86_64.*|aarch64.*)'

BBCLASSEXTEND = "native nativesdk"
