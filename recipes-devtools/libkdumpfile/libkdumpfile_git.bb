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
SRCREV = "fedbe7f66561754782165be2512bca0ed1b775e9"

PV = "v0.4.0+git${SRCPV}"

SRC_URI = "git://github.com/ptesarik/libkdumpfile;branch=${SRCBRANCH} \
           file://0001-added-way-to-read-NUMBER-values-from-vmcoreinfo.patch \
           file://0002-addrxlat-aarch64-add-linux-vmcore-support-for-kernel.patch \
          "

S = "${WORKDIR}/git"

DEPENDS = "zlib python3"

inherit autotools pkgconfig python3native python3-dir

PACKAGECONFIG ??= ""
PACKAGECONFIG[lzo] = "--with-lzo,--without-lzo,lzo"
PACKAGECONFIG[snappy] = "--with-snappy,--without-snappy,snappy"

# remove examples binaries
do_install_append () {
        rm -rf "${D}${bindir}"
}

PACKAGES += "python3-libkdumpfile"
FILES_python3-libkdumpfile += "${PYTHON_SITEPACKAGES_DIR}"

COMPATIBLE_HOST = '(x86_64.*|aarch64.*)'

BBCLASSEXTEND = "native"